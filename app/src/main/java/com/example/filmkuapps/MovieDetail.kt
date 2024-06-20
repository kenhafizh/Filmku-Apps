package com.example.filmkuapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.filmkuapps.databinding.ActivityMovieDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    lateinit var apiService: ApiInterface
    var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = intent.getParcelableExtra("movie")

        binding.movieTitle.text = movie?.title
        binding.movieDesc.text = movie?.overview

        //pakai this untuk context pada activity
        Glide.with(this@MovieDetail)
            .load(movie?.getMovies())
            .into(this.binding.tvPoster)


        apiService = ApiService().getService()

        getDetails(movie!!)
    }

        fun getDetails(movie: Movie){
            apiService.getMovieDetails(movie.id!!).enqueue(object: Callback<DetailsMovie>{
                override fun onResponse(p0: Call<DetailsMovie>, data: Response<DetailsMovie>) {
                    Log.e("MainActivity", "onResponse : ${data.body()}",)

                    data.body()?.genres.let { genres ->
                        val genreString = genres?.joinToString(", ") { it.name!! }
                        binding.movieGenre.text = genreString
                    }
                }


                override fun onFailure(p0: Call<DetailsMovie>, data: Throwable) {
                    Log.e("MainActivity", "onFailure : ${data.message}",)
                }

            })
        }


}