package domain.model

import data.model.MoviesResponse
import getPlatform

data class MovieModel(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

fun MoviesResponse.toDomain() : List<MovieModel> =
    results.map { data ->
        MovieModel(
            adult = data.adult,
            backdropPath = getPlatform().imageBaseUrl + data.backdropPath,
            genreIds = data.genreIds,
            id = data.id,
            originalLanguage = data.originalLanguage,
            originalTitle = data.originalTitle,
            overview = data.overview,
            popularity = data.popularity,
            posterPath = getPlatform().imageBaseUrl + data.posterPath,
            releaseDate = data.releaseDate,
            title = data.title,
            video = data.video,
            voteAverage = data.voteAverage,
            voteCount = data.voteCount,
        )
    }

