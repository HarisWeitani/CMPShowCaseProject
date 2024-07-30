package org.hariswei.cmpshowcaseproject.data.repository

import org.hariswei.cmpshowcaseproject.data.network.ApiService
import org.hariswei.cmpshowcaseproject.domain.model.MovieModel
import org.hariswei.cmpshowcaseproject.domain.model.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.hariswei.cmpshowcaseproject.data.database.MovieDatabase
import org.hariswei.cmpshowcaseproject.data.model.MovieEntity
import org.hariswei.cmpshowcaseproject.data.model.MoviesResponse
import org.hariswei.cmpshowcaseproject.util.NetworkError
import org.hariswei.cmpshowcaseproject.util.Result
import org.hariswei.cmpshowcaseproject.util.UiState
import org.hariswei.cmpshowcaseproject.util.onError
import org.hariswei.cmpshowcaseproject.util.onSuccess

interface MoviesRepository {
    suspend fun getMovies() : Result<MoviesResponse, NetworkError>
    suspend fun getMoviesDB() : List<MovieEntity>
    suspend fun upsertMovies(data: List<MovieEntity>)
}

class MoviesRepositoryImpl(
    private val moviesClient: ApiService,
    private val database: MovieDatabase
): MoviesRepository {
    override suspend fun getMovies(): Result<MoviesResponse, NetworkError> {
        return moviesClient.getMovies(1)
    }

    override suspend fun getMoviesDB(): List<MovieEntity> {
        return database.movieDao().getMovies()
    }

    override suspend fun upsertMovies(data: List<MovieEntity>) {
        return database.movieDao().upsertMovies(data)
    }
}
