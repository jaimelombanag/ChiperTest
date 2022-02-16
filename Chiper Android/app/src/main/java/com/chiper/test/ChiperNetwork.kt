package com.chiper.test


import app.kaisa.tmdb.model.Movie
import app.kaisa.tmdb.model.MovieResponse
import app.kaisa.tmdb.model.MovieVideosResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface ChiperNetwork {

    @GET("movie/popular")
    fun getMoviesPopular(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getMoviesTopRated(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getMoviesUpcoming(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: Int): Call<Movie>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") id: Int): Call<MovieVideosResponse>

    @GET("search/movie")
    fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<MovieResponse>


    companion object {
        val api by lazy { create() }

        private fun create(): ChiperNetwork {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val original = chain.request()
                        val originalHttpUrl = original.url()

                        val url = originalHttpUrl.newBuilder()
                            .addQueryParameter("api_key", Constants.API_KEY)
                            .build()

                        val requestBuilder = original.newBuilder()
                            .url(url)

                        val request = requestBuilder.build()
                        return chain.proceed(request)
                    }
                })
                .build()

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ChiperNetwork::class.java)
        }
    }
}