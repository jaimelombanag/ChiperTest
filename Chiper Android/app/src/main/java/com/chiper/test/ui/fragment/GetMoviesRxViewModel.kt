package com.chiper.test.ui.fragment

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import androidx.paging.rxjava2.cachedIn
import androidx.room.Room
import com.chiper.test.application.MyApp.Companion.context
import io.reactivex.Flowable


class GetMoviesRxViewModel(private val repository: GetMoviesRxRepository) : ViewModel() {
    @SuppressLint("CheckResult")
    fun getFavoriteMovies(): Flowable<PagingData<Movies.Movie>> {
        return repository
            .getMovies()
            .map { pagingData -> pagingData.filter { it.poster != null } }
            .cachedIn(viewModelScope)
    }
}