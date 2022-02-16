package com.chiper.test.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAlphabetizedWords(): Flow<MutableList<Movies>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movies: Movies)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()
}
