package com.example.filmkuapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmkuapps.databinding.MovieListAdapterBinding

class MovieListAdapter(val listener: onMovieClickListener) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    interface onMovieClickListener {
        fun onClick(movie: Movie, index: Int)
    }

    var items = listOf<Movie>()
    var details = listOf<DetailsMovie>()

    class MovieViewHolder(val binding: MovieListAdapterBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieListAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false

        )
        return MovieViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie= items[position]


        val posterPath = "https://image.tmdb.org/t/p/w500/" + movie.posterPath
        Glide.with(holder.itemView.context). load(posterPath).into(holder.binding.movieImage)

        holder.binding.movieName.text = movie.title
        holder.binding.root.setOnClickListener {
          listener.onClick(movie, position)
        }
        holder.binding.btnFavorite

    }

}