package com.chiper.test.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    val total: Int = 0,
    val page: Int = 0,
    val movies: List<Movie>
) : Parcelable {

    @IgnoredOnParcel
    val endOfPage = total == page

    @Parcelize
    @Entity(tableName = "movies")
    data class Movie(
        //@PrimaryKey(autoGenerate = true) val id: Long = 0,
        @PrimaryKey(autoGenerate = true) val _id: Int,
        @ColumnInfo(name = "movieId") val movieId: Long,
        @ColumnInfo(name = "popularity") val popularity: Double,
        @ColumnInfo(name = "video") val video: Boolean,
        @ColumnInfo(name = "poster") val poster: String?,
        @ColumnInfo(name = "adult") val adult: Boolean,
        @ColumnInfo(name = "backdrop") val backdrop: String?,
        @ColumnInfo(name = "originalLanguage") val originalLanguage: String,
        @ColumnInfo(name = "originalTitle") val originalTitle: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "voteAverage") val voteAverage: Double,
        @ColumnInfo(name = "overview") val overview: String,
        @ColumnInfo(name = "releaseDate") val releaseDate: String?
    ) : Parcelable

    @Parcelize
    @Entity(tableName = "movie_remote_keys")
    data class MovieRemoteKeys(
        @PrimaryKey val movieId: Long,
        val prevKey: Int?,
        val nextKey: Int?
    ) : Parcelable
}
