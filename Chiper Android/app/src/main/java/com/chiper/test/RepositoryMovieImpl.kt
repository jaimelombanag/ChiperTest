package com.chiper.test

import com.chiper.test.model.MovieNowPlaying



class RepositoryMovieImpl(private val dataSource: DataSource): IRepositoryMovie {

    override suspend fun movieNowPlaying(): Resource.Success<MovieNowPlaying> {
        return dataSource.movieNowPlaying(Constants.API_KEY, "es-ES", "1")
    }

}