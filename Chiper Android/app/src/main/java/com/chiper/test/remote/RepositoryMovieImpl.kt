package com.chiper.test.remote


import com.chiper.test.application.Constants
import com.chiper.test.application.Resource
import com.chiper.test.data.model.MovieNowPlaying
import com.chiper.test.data.source.DataSource
import com.chiper.test.remote.IRepositoryMovie


class RepositoryMovieImpl(private val dataSource: DataSource): IRepositoryMovie {

    override suspend fun movieNowPlaying(): Resource.Success<MovieNowPlaying> {
        return dataSource.movieNowPlaying(Constants.API_THE_MOVIE_DB, "es-ES", "1")
    }

}