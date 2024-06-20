package com.example.filmkuapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.filmkuapps.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), MovieListAdapter.onMovieClickListener, SwipeRefreshLayout.OnRefreshListener {
    lateinit var apiService: ApiInterface
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.swipeRefresh.setOnRefreshListener(this)

        //Start Shimmer
        binding.shimmerFrameLayout.startShimmer()


        adapter = MovieListAdapter(this)
        binding.recylerView.adapter = adapter
        binding.recylerView.layoutManager = LinearLayoutManager(this)

        apiService = ApiService().getService()

        getMovies()

        binding.btnRetry.setOnClickListener {
            getMovies()
        }

    }

        fun getMovies() {
            apiService.getMoviesPopular().enqueue(object: Callback<MovieListResponse>{
                override fun onResponse(p0: Call<MovieListResponse>, data: Response<MovieListResponse>) {
                    Log.e("MainActivity", "onResponse : ${data.body()}",)
                    if (data.body() != null) {
                        adapter.items = data.body()!!.results
                        adapter.notifyDataSetChanged()

                    binding.swipeRefresh.isRefreshing = false

                    binding.shimmerFrameLayout.stopShimmer()
                    binding.shimmerFrameLayout.isVisible = false

                    binding.recylerView.isVisible = true
                    binding.errorText.isVisible = false
                    binding.btnRetry.isVisible = false
                }
            }
            override fun onFailure(p0: Call<MovieListResponse>, data: Throwable) {
                    Log.e("MainActivity", "onFailure : ${data.message}",)

                //munculkan teks saat data tidak dapat dimuat
                //SwipeToRefresh
                //gunakan button, panggil getmovies.
                binding.swipeRefresh.isRefreshing = false
                binding.shimmerFrameLayout.stopShimmer()
                binding.shimmerFrameLayout.isVisible = false
                binding.recylerView.isVisible = false
                binding.errorText.isVisible = true
                binding.btnRetry.isVisible = true
                binding.errorText.text = "Koneksi Tidak Stabil"
            }
        })
    }

//    fun getTopRated(){
//        apiService.getMoviesTopRated().enqueue(object: Callback<MovieListResponse>{
//            override fun onResponse(p0: Call<MovieListResponse>, data: Response<MovieListResponse>) {
//                Log.e("Main Activity", "onResponse : ${data.body()}",)
//                if (data.body() != null) {
//                    adapter.items = data.body()!!.results
//                    adapter.notifyDataSetChanged()
//
//                }
//            }
//
//            override fun onFailure(p0: Call<MovieListResponse>, data: Throwable) {
//                Log.e("Main Activity", "onFailure : ${data.message}",)
//            }
//        })
//    }

    override fun onClick(movie: Movie, index: Int) {
//        Log.d("MainActivityClick", "on click ${movie.title} pada indkes $index rilis pada tanggal ${movie.releaseDate}")
        val intent = Intent(this, MovieDetail::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)

    }

    override fun onRefresh() {
        binding.shimmerFrameLayout.startShimmer()
        binding.shimmerFrameLayout.isVisible = true

        binding.errorText.isVisible = false
        binding.btnRetry.isVisible = false

        adapter.items = listOf()
        adapter.notifyDataSetChanged()

        getMovies()

    }

}

//ada 2 action yang perlu dilakukan
//1. search, 2.pagination