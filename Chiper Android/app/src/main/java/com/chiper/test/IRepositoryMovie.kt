package com.chiper.test



import com.chiper.test.model.MovieNowPlaying


interface IRepositoryMovie {
    //movieNowPlaying
    suspend fun movieNowPlaying(): Resource.Success<MovieNowPlaying>
}