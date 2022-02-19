package com.chiper.test.repository


import androidx.paging.PagingData
import com.chiper.test.model.Movies
import io.reactivex.Flowable

interface MoviesRepository {
    fun getMovies(): Flowable<PagingData<Movies.Movie>>
}