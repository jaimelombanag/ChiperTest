package com.chiper.test

import com.chiper.test.model.MovieNowPlaying


class DataSource {


    //movieNowPlaying
    suspend fun movieNowPlaying(
        api_key: String,
        language: String,
        page: String
    ): Resource.Success<MovieNowPlaying>{
        return Resource.Success(RetrofitService.webServicesMovie.movieNowPlaying(api_key, language, page))
    }

}