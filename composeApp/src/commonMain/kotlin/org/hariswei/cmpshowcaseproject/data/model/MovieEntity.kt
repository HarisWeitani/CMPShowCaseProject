package org.hariswei.cmpshowcaseproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int,
    val adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
)

fun MoviesResponse.toEntity() : List<MovieEntity> =
    results.map { data ->
        MovieEntity(
            movieId = data.id,
            adult = data.adult,
            backdropPath = data.backdropPath,
            originalLanguage = data.originalLanguage,
            originalTitle = data.originalTitle,
            overview = data.overview,
            popularity = data.popularity,
            posterPath = data.posterPath,
            releaseDate = data.releaseDate,
            title = data.title,
            video = data.video,
            voteAverage = data.voteAverage,
            voteCount = data.voteCount
        )
    }
