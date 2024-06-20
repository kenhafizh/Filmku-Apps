package com.example.filmkuapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Index
import com.bumptech.glide.Glide
import com.example.filmkuapps.databinding.MovieSearchAdapterBinding

class MovieSearchAdapter(val listener : onClickListener) : RecyclerView.Adapter<MovieSearchAdapter.MovieViewHolder>() {


    interface onClickListener{
        fun onClick(movie: Movie, Index: Int)
        fun onFavorite(movie: Movie)
        fun onDelete(movie: Movie, Index: Int)
    }


    var items = listOf<Movie>()

    class MovieViewHolder(val binding: MovieSearchAdapterBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = MovieSearchAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = items[position]

        val posterPath = "https://image.tmdb.org/t/p/w500/" + movie.posterPath
        Glide.with(holder.itemView.context)
            .load(posterPath)
            .into(holder.binding.movieImage)
            .onLoadFailed(ContextCompat.getDrawable(holder.itemView.context, R.drawable.no_picture))

        holder.binding.movieName.text = movie.title
        holder.binding.btnFavorite.setOnClickListener {
            listener.onFavorite(movie)
            holder.binding.btnRemove.isVisible = true
        }

        holder.binding.btnRemove.setOnClickListener {
            listener.onDelete(movie, position)
            holder.binding.btnRemove.isVisible = false
        }
    }
}


//        if(movie.isFavorite){
//            holder.binding.btnRemove.isVisible = true
//            holder.binding.btnFavorite.isVisible = false
//        } else {
//            holder.binding.btnRemove.isVisible = false
//            holder.binding.btnFavorite.isVisible = true
//        }

//didalam adapter adalah semua proses dengan isi recylerview
//jangan ada proses yang tidak berkaitan dengan recylerview didalam adapter, contoh ngisi database jangan didalam adapter
