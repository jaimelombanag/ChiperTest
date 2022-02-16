package com.chiper.test.ui.peliculas

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiper.test.application.BaseViewHolder
import com.chiper.test.application.Constants
import com.chiper.test.data.room.Movies
import com.chiper.test.databinding.MovieCardBinding


class AdapterMoviesRoom(
    private val context: Context,
    private val moviesList: List<Movies>,
    private val itemClickListener: onMovieClickListenerRoom
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface onMovieClickListenerRoom{
        fun onMovieClickRoom(result: Movies, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MovieCardBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = MainViewHolder(itemBinding)
        //return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.tragos_row, parent, false)) //funciona con synthetic

        itemBinding.root.setOnClickListener{
            val position = holder.adapterPosition.takeIf { it != NO_POSITION } ?: return@setOnClickListener
            itemClickListener.onMovieClickRoom(moviesList[position], position)
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(moviesList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class MainViewHolder(val binding: MovieCardBinding): BaseViewHolder<Movies>(binding.root){
        override fun bind(item: Movies, position: Int) = with(binding){
            Glide.with(context).load("${Constants.IMG_MOVIE_DB}${item.posterPath}").centerCrop().into(imvMovie)
            tvTituloPelicula.text = item.title
            tvRatePelicula.text = "Calificación: ${item.voteAverage}"
        }
    }
}