package com.chiper.test.data.source

import com.chiper.test.application.Resource
import com.chiper.test.application.RetrofitService
import com.chiper.test.data.model.MovieNowPlaying


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