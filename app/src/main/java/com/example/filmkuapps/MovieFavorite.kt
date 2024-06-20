package com.example.filmkuapps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmkuapps.databinding.ActivityMovieDetailBinding
import com.example.filmkuapps.databinding.ActivityMovieFavortieBinding

class MovieFavorite: AppCompatActivity(), MovieFavoriteAdapter.OnCLickListener{

    lateinit var binding: ActivityMovieFavortieBinding
    lateinit var adapter: MovieFavoriteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieFavortieBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = MovieFavoriteAdapter(this)
        binding.recylerViewFavorite.adapter = adapter
        binding.recylerViewFavorite.layoutManager = LinearLayoutManager(this)

    }

}

//Ambil data dari Database bukan dari halaman sebelumnya