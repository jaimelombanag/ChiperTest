package com.chiper.test.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chiper.test.R
import coil.load
import com.chiper.test.databinding.MovieGridItemBinding


class MovieGridViewHolder(private val binding: MovieGridItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movies.Movie) {
        with(movie) {
            binding.poster.load(poster?.medium) {
                crossfade(true)
            }
            itemView.setOnClickListener {
                Log.i("Movies", "==========SE OPRIME  ${movie.title}")
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): MovieGridViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_grid_item,  parent,false)

            val binding = MovieGridItemBinding.bind(view)

            return MovieGridViewHolder(
                binding
            )
        }
    }

}
