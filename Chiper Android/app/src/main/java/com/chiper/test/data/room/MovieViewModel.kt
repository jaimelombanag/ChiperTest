package com.chiper.test.data.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {


    val allMovies: LiveData<MutableList<Movies>> = repository.allMovies.asLiveData()


    fun insert(movies: Movies) = viewModelScope.launch {
        repository.insert(movies)
    }

    fun delete() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
