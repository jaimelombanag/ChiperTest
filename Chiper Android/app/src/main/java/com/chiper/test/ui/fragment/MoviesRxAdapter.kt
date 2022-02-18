package com.chiper.test.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiper.test.application.BaseViewHolder
import com.chiper.test.application.Constants
import com.chiper.test.application.MyApp.Companion.context
import com.chiper.test.data.model.Result
import com.chiper.test.databinding.MovieCardBinding
import com.chiper.test.databinding.MovieGridItemBinding
import com.chiper.test.ui.peliculas.AdapterMovies
import com.google.android.material.internal.NavigationMenuItemView


class MoviesRxAdapter : PagingDataAdapter<Movies.Movie, MovieGridViewHolder>(
    COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGridViewHolder {
        return MovieGridViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieGridViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movies.Movie>() {
            override fun areItemsTheSame(oldItem: Movies.Movie, newItem: Movies.Movie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: Movies.Movie, newItem: Movies.Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

}
