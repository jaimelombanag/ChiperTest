package com.chiper.test.data.source

import com.chiper.test.application.Constants
import com.chiper.test.application.Resource
import com.chiper.test.application.RetrofitService
import com.chiper.test.data.model.MovieNowPlaying


class DataSource {


    //movieNowPlaying
//    suspend fun movieNowPlaying(
//        api_key: String,
//        language: String,
//        page: String
//    ): Resource.Success<MovieNowPlaying>{
//        return Resource.Success(RetrofitService.webServicesMovie.movieNowPlaying(api_key, language, page))
//    }

    suspend fun loadMovies(
        page: String
    ): Resource.Success<MovieNowPlaying>{
        return Resource.Success(RetrofitService.webServicesMovie.loadMovies())
        //return Resource.Success(RetrofitService.webServicesMovie.movieNowPlaying(Constants.API_THE_MOVIE_DB, "es-ES", page))
    }

}