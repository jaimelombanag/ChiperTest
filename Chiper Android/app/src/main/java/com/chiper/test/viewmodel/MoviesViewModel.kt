package com.chiper.test.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import com.chiper.test.model.Movies
import com.chiper.test.repository.MoviesRepository
import io.reactivex.Flowable


class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {
    @SuppressLint("CheckResult")
    fun getFavoriteMovies(): Flowable<PagingData<Movies.Movie>> {
        return repository
            .getMovies()
            .map { pagingData -> pagingData.filter { it.poster != null } }
            .cachedIn(viewModelScope)
    }
}