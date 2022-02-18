package com.chiper.test.ui.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.chiper.test.application.MyApp

class ViewModelRoom(app: Application) : AndroidViewModel(app) {

    val db = Room.databaseBuilder(
        MyApp.context,
        MoviesDatabase::class.java, "movies"
    ).build()
        .moviesDao()


    val items = Pager(
        PagingConfig(
            pageSize = 50,
            enablePlaceholders = true,
            maxSize = 200
        )
    ){
        db.getAlphabetizedWords()
    }.flow





}