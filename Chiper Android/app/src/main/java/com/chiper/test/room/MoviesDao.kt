package com.chiper.test.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chiper.test.model.Movies

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies ORDER BY _id DESC")
    fun getAlphabetizedWords(): PagingSource<Int, Movies.Movie>

    @Query("SELECT * FROM movies")
    fun getMovies(): List<Movies.Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: Movies.Movie) : Long

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}
