package com.chiper.test.application

import com.google.gson.GsonBuilder
import com.chiper.test.data.remoteApi.WebServicesMovieDB
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    var gson = GsonBuilder()
        .setLenient()
        .create()

    val webServicesMovie: WebServicesMovieDB by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_THE_MOVIE_DB_V4)
            //.baseUrl(Constants.BASE_URL_THE_MOVIE_DB)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WebServicesMovieDB::class.java)
    }
}