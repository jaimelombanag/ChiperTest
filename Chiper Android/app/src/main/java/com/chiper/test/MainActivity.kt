package com.chiper.test


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chiper.test.databinding.ActivityMainBinding
import com.chiper.test.application.MyApp
import com.chiper.test.data.room.MovieViewModel
import com.chiper.test.data.room.MovieViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val wordViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory((application as MyApp).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        wordViewModel.allWords.observe(this) { words ->
//            // Update the cached copy of the words in the adapter.
//            Log.i("Movies", ".....  " + words.toString())
//
//        }



        //val movie = Movies("valerie la bella", "Esta es la pelicula de mi princesa hermosa que amo con todo mi corazon", "la imagen" )
        //wordViewModel.insert(movie)
        //wordViewModel.delete()

    }





}