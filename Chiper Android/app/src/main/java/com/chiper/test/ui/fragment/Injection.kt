package com.chiper.test.ui.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.chiper.test.R
import java.util.*

object Injection {
    fun provideLocale(): Locale = Locale.getDefault()




    fun provideRxViewModel(context: Context): ViewModelProvider.Factory {
        val pagingSource =
            GetMoviesRxPagingSource(
                service = TMDBService.create(),
                apiKey = "",
                mapper = MoviesMapper(),
                locale = provideLocale()
            )

        val repository =
            GetMoviesRxRepositoryImpl(
                pagingSource = pagingSource
            )

        return GetMoviesRxViewModelFactory(
            repository
        )
    }


}