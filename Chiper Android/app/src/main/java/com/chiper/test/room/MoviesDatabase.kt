package com.chiper.test.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chiper.test.model.Movies


@Database(entities = arrayOf(Movies.Movie::class), version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}