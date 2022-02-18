package com.chiper.test.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.chiper.test.R
import coil.load
import com.chiper.test.databinding.MovieGridItemBinding
import androidx.navigation.fragment.findNavController
import com.chiper.test.application.Constants


class MovieGridViewHolder(private val binding: MovieGridItemBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(movie: Movies.Movie) {
        with(movie) {
            //binding.poster.load(poster?.medium) {
            //    crossfade(true)
            //}

            binding.poster.load("${Constants.IMAGE_MEDIUM}$poster")

            binding.title.text = title

            itemView.setOnClickListener {
                Log.i("Movies", "==========SE OPRIME  ${movie.title}")
                val bundle = Bundle()
                bundle.putParcelable("result", movie)
                val view: View = binding.root
                view.findNavController()?.navigate(R.id.action_navigation_movies_to_peliculaDetalleFragment, bundle)

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
