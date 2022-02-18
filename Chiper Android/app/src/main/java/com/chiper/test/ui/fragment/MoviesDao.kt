package com.chiper.test.ui.fragment

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {


    @Query("SELECT * FROM movies ORDER BY _id DESC")
    fun getAlphabetizedWords(): PagingSource<Int, Movies.Movie>


    @Query("SELECT * FROM movies")
    fun getMovies(): List<Movies.Movie>



//    @Query("SELECT * FROM movies")
//    fun getAlphabetizedWords(): List<Movies.Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: Movies.Movie) : Long


    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    //suspend fun insertPaging(movies: PagingData<Movies.Movie>)


    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}
