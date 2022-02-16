package com.chiper.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MyApp.instance.repository)
    }
    private val peliculaViewModel by viewModels<PeliculasViewModel> {
        PeliculasViewModelFactory(
            RepositoryMovieImpl(DataSource())
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()

    }

    fun setupObservers(){
        peliculaViewModel.movieNowPlaying().observe(this, Observer {

            Log.i("Movies", "------La lista es: $it")

//            when(it){
//                is Resource.Loading ->{
//                    binding.progressBar.visibility = View.VISIBLE
//                }
//                is Resource.Success ->{
//                    binding.progressBar.visibility = View.GONE
//                    binding.rvMovieNowPlaying.adapter = AdapterMovies(requireContext(),  it.data.data.results, this)
//                    movieViewModel.delete()
//                    saveMovies(it.data.data.results)
//                }
//                is Resource.Failure ->{
//                    binding.progressBar.visibility = View.GONE
//                    movieViewModel.allMovies.observe(this) { movie ->
//                        Log.i("Movies", "------EL tamaño de la lista es: " + movie.size)
//                        binding.rvMovieNowPlaying.adapter = AdapterMoviesRoom(requireContext(),  movie, this)
//                    }
//                    Toast.makeText(requireContext(), "Error extracion de datos ${it.exception}", Toast.LENGTH_SHORT).show()
//                }
//            }
        })
    }
}