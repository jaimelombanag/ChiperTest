package com.chiper.test.data.remoteApi


import com.chiper.test.application.Constants
import com.chiper.test.data.model.MovieNowPlaying
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WebServicesMovieDB {

    @Headers("Content-Type: application/json; charset=UTF-8")
    @GET("movie/popular")
    suspend fun movieNowPlaying(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): MovieNowPlaying

    @Headers("Content-Type: application/json; charset=UTF-8")
    @GET(Constants.MOVIE_GENERO)
    suspend fun movieGenero(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): MovieNowPlaying



    @Headers(
        "Authorization: ${Constants.TOKEN_ACCESS}"
    )
    @GET("list/1")
    suspend fun loadMovies(): MovieNowPlaying



}