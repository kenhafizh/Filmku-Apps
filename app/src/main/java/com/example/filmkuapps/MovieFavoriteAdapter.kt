package com.example.filmkuapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmkuapps.databinding.MovieFavoriteAdapterBinding

class MovieFavoriteAdapter(val listener: OnCLickListener) : RecyclerView.Adapter<MovieFavoriteAdapter.MovieViewHolder>() {

    interface OnCLickListener {
//        fun onFavorite(movie: Movie)
    }

    var items = listOf<Movie>()

    class MovieViewHolder(val binding: MovieFavoriteAdapterBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val binding = MovieFavoriteAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieFavoriteAdapter.MovieViewHolder, position: Int) {
        val movie = items[position]

        val posterPath = "https://image.tmdb.org/t/p/w500/" + movie.posterPath
        Glide.with(holder.itemView.context)
            .load(posterPath)
            .into(holder.binding.movieImageFavorite)
            .onLoadFailed(ContextCompat.getDrawable(holder.itemView.context, R.drawable.no_picture))

        holder.binding.movieNameFavorite.text = movie.title
        holder.binding.btnRemoveMovie
    }

    override fun getItemCount(): Int {
        return items.size
    }


}