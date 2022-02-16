/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chiper.test

import androidx.annotation.WorkerThread
import app.kaisa.tmdb.model.Movie
import com.chiper.test.room.MovieDao
import kotlinx.coroutines.flow.Flow


class MovieRepository(private val movieDao: MovieDao) {

    val allMovies: Flow<MutableList<Movie>> = movieDao.loadMovies()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movies: Movie) {
        movieDao.insert(movies)
    }

    suspend fun deleteAll(){
        movieDao.deleteAll()
    }
}
