package com.chiper.test.model

import java.util.*

class MoviesMapper {

    fun transform(response: MoviesResponse, locale: Locale): Movies {
        return with(response) {
            Movies(
                total = total,
                page = page,
                movies = results.map {
                    Movies.Movie(
                        0,
                        it.id,
                        it.popularity,
                        it.video,
                        it.posterPath,
                        it.adult,
                        it.backdropPath,
                        it.originalLanguage,
                        it.originalTitle,
                        it.title,
                        it.voteAverage,
                        it.overview,
                        it.releaseDate
//                        it.releaseDate?.let { date ->
//                            if (date.isNotEmpty()) {
//                                SimpleDateFormat("YYYY-mm-dd", locale).parse(date)
//                            } else {
//                                null
//                            }
//                        }
                    )
                }
            )
        }
    }
}