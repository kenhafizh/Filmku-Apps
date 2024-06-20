package com.example.filmkuapps

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmkuapps.databinding.ActivitySearchMovieBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMovieActivity : AppCompatActivity(), MovieSearchAdapter.onClickListener {

    lateinit var itemDatabase: ItemDatabase
    lateinit var itemDao: ItemDao

    lateinit var binding: ActivitySearchMovieBinding
    lateinit var adapter: MovieSearchAdapter
    lateinit var apiService: ApiInterface

    lateinit var btnFavorite: Button
    var id = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMovieBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        adapter = MovieSearchAdapter(this)
        binding.searchRecylerView.adapter = adapter
        binding.searchRecylerView.layoutManager = LinearLayoutManager(this)

        //Start Shimmer


        apiService = ApiService().getService()

        binding.btnSearch.setOnClickListener {
            closeKeyboard(binding.inputText)
            binding.shimmerFrameSearch.startShimmer()
            binding.shimmerFrameSearch.isVisible = true
            getSearchMovie(binding.inputText.text.toString())
            binding.txtNotFound.isVisible = false
            adapter.items = listOf()
            adapter.notifyDataSetChanged()

        }

        binding.btnRefreshing.setOnClickListener {
            binding.btnRefreshing.isVisible =  false
            binding.inputText.isVisible = true
            binding.btnSearch.isVisible = true
            binding.txtConnection.isVisible = false
        }

        binding.btnFavorite1.setOnClickListener {
            val intent = Intent(this, MovieFavorite::class.java)
//            intent.putExtra("movie", item )
            startActivity(intent)
//            getSearchMovie(binding.inputText.text.toString())
//            adapter.items = listOf()
//            adapter.notifyDataSetChanged()
        }



        itemDatabase = ItemDatabase.getInstance(this)
        itemDao = itemDatabase.itemDao()

    }


    fun getSearchMovie(movie: String){
        apiService. getSearch(movie).enqueue(object: Callback<MovieListResponse>{
            override fun onResponse(p0: Call<MovieListResponse>, data: Response<MovieListResponse>) {
                Log.e("MainActivity", "onResponse : ${data.body()}",)
                if (data.body() != null) {
                    val favorites = mutableListOf<Movie>()
                    data.body()!!.results.forEach { film ->
                        val result = itemDao.getId(film.id)
                        if (result != null){
                            film.isFavorite = true
                        }
                        favorites.add(film)
                    }

                    adapter.items = favorites
                    adapter.notifyDataSetChanged()

                    binding.shimmerFrameSearch.stopShimmer()
                    binding.shimmerFrameSearch.isVisible = false

                    binding.txtNotFound.text = "Film tidak ditemukan"
                    binding.txtNotFound.isVisible = true
                }
            }

            override fun onFailure(p0: Call<MovieListResponse>, data: Throwable) {
                Log.e("MainActivity", "onFailure : ${data.message}",)

                binding.shimmerFrameSearch.stopShimmer()
                binding.shimmerFrameSearch.isVisible = false

                binding.txtConnection.text
                binding.txtConnection.isVisible = true
                binding.btnSearch.isVisible = false
                binding.inputText.isVisible = false
                binding.btnRefreshing.isVisible = true

            }

        })
    }


    private fun closeKeyboard(view: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    override fun onClick(movie: Movie, Index: Int) {

    }

    override fun onFavorite(movie: Movie) {
        val item = Item(id = movie.id!!.toLong(), name = movie.title!!)
        itemDao.insert(item)
    }

    override fun onDelete(movie: Movie, Index: Int) {
        val item = Item(id = movie.id!!.toLong(), name = movie.title!!)
        itemDao.delete(item)
    }


}


//1. Belum ada loading (DONE)
//2. Belum ada error handling nya
//3. Hide soft keyboard ketika button (DONE)
//4. Ketika sudah search, tunggu loading nya selesai sebelum search lagi.
//5. pagination