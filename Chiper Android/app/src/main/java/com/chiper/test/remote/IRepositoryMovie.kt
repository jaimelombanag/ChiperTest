package com.chiper.test.remote


import com.chiper.test.application.Resource
import com.chiper.test.data.model.MovieNowPlaying


interface IRepositoryMovie {
    //movieNowPlaying
    //suspend fun movieNowPlaying(): Resource.Success<MovieNowPlaying>
    //loadMovies
    suspend fun loadMovies(): Resource.Success<MovieNowPlaying>
}