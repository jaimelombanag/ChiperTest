package com.chiper.test

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.chiper.test.api.ApiService
import com.chiper.test.model.MoviesMapper
import com.chiper.test.repository.MoviesRepositoryImpl
import com.chiper.test.api.GetMoviesPagingSource
import com.chiper.test.viewmodel.MoviesViewModelFactory
import java.util.*

object Injection {
    fun provideLocale(): Locale = Locale.getDefault()




    fun provideRxViewModel(context: Context): ViewModelProvider.Factory {
        val pagingSource =
            GetMoviesPagingSource(
                service = ApiService.create(),
                apiKey = "",
                mapper = MoviesMapper(),
                locale = provideLocale()
            )

        val repository =
            MoviesRepositoryImpl(
                pagingSource = pagingSource
            )

        return MoviesViewModelFactory(
            repository
        )
    }


}