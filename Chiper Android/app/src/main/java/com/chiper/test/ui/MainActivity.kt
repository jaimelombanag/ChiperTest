package com.chiper.test.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chiper.test.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



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