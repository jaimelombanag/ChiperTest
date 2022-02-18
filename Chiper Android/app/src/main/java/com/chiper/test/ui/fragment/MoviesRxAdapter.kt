package com.chiper.test.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.chiper.test.application.BaseViewHolder
import com.chiper.test.application.Constants
import com.chiper.test.application.MyApp
import com.chiper.test.application.MyApp.Companion.context
import com.chiper.test.data.model.Result
import com.chiper.test.databinding.MovieCardBinding
import com.chiper.test.databinding.MovieGridItemBinding
import com.chiper.test.ui.peliculas.AdapterMovies
import com.google.android.material.internal.NavigationMenuItemView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MoviesRxAdapter() : PagingDataAdapter<Movies.Movie, MovieGridViewHolder>(
    COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGridViewHolder {
        return MovieGridViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieGridViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
            GlobalScope.launch(Dispatchers.Main) {
                val db = Room.databaseBuilder(
                    context,
                    MoviesDatabase::class.java, "movies"
                ).build()
                val movieDao = db.moviesDao()
                Log.i("TAG", "------SAVE DATA-------")
                movieDao.insert(it)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movies.Movie>() {
            override fun areItemsTheSame(oldItem: Movies.Movie, newItem: Movies.Movie): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Movies.Movie, newItem: Movies.Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

}
