package com.chiper.test.ui.fragment

import androidx.compose.ui.unit.Constraints
import com.chiper.test.application.Constants
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TMDBService {

    @GET("discover/movie?sort_by=original_title.asc&region=ID")
    suspend fun moviesFlow(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ) : MoviesResponse

//    @GET("movie/popular")
//    fun popularMovieRx(
//        @Query("api_key") apiKey: String,
//        @Query("page") page: Int,
//        @Query("language") language: String
//    ) : Single<MoviesResponse>


        @Headers(
            "Authorization: ${Constants.TOKEN_ACCESS}"
        )
        @GET("list/1")
        fun popularMovieRx2(
            @Query("page") page: String,
        ) : Single<MoviesResponse>

    companion object {

        fun create(): TMDBService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_THE_MOVIE_DB_V4)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(TMDBService::class.java)
        }
    }
}