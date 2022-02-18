package com.chiper.test.ui.fragment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Movies.Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun MoviesDatabase(): MoviesDao
}