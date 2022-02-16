package com.chiper.test.presentation

import androidx.lifecycle.*
import com.chiper.test.application.Resource
import com.chiper.test.remote.IRepositoryMovie

import kotlinx.coroutines.Dispatchers

class PeliculasViewModel(private val repositoryMovie: IRepositoryMovie) : ViewModel() {



    fun movieNowPlaying() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            //Log.i("Movies",
            //    Gson().toJson(Resource.Success(repositoryMovie.movieNowPlaying()))
            //)

            //val list: List<MovieModel> = Resource.Success(repositoryMovie.movieNowPlaying()).data.data.results as List<MovieModel>

            //addMovies(list)

            emit(Resource.Success(repositoryMovie.movieNowPlaying()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }



}

class PeliculasViewModelFactory(private val repositoryMap: IRepositoryMovie): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IRepositoryMovie::class.java).newInstance(repositoryMap)
    }
}