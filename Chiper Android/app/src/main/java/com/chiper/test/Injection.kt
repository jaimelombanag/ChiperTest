package com.chiper.test

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.chiper.test.api.TMDBService
import com.chiper.test.model.MoviesMapper
import com.chiper.test.repository.GetMoviesRxRepositoryImpl
import com.chiper.test.api.GetMoviesRxPagingSource
import com.chiper.test.viewmodel.GetMoviesRxViewModelFactory
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