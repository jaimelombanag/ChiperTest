package com.chiper.test.ui.fragment


import androidx.paging.PagingData
import io.reactivex.Flowable

interface GetMoviesRxRepository {
    fun getMovies(): Flowable<PagingData<Movies.Movie>>
}