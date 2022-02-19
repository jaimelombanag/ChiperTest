package com.chiper.test.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.chiper.test.api.GetMoviesPagingSource
import com.chiper.test.model.Movies
import io.reactivex.Flowable

class MoviesRepositoryImpl(private val pagingSource: GetMoviesPagingSource):
    MoviesRepository {

    override fun getMovies(): Flowable<PagingData<Movies.Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40),
            pagingSourceFactory = { pagingSource }
        ).flowable
    }
}