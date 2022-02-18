package com.chiper.test.ui.fragment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAlphabetizedWords(): Movies.Movie

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movies: Movies.Movie)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}
