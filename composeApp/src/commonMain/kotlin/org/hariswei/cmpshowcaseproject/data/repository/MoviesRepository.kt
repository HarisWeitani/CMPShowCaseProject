package org.hariswei.cmpshowcaseproject.data.repository

import database.MovieDatabase
import org.hariswei.cmpshowcaseproject.data.network.ApiService
import org.hariswei.cmpshowcaseproject.model.MovieModel
import org.hariswei.cmpshowcaseproject.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.hariswei.cmpshowcaseproject.util.UiState
import org.hariswei.cmpshowcaseproject.util.onError
import org.hariswei.cmpshowcaseproject.util.onSuccess

interface MoviesRepository {
    suspend fun getMovies() : Flow<UiState<List<MovieModel>>>
    suspend fun insertMovies(dataList: List<MovieModel>)
}

class MoviesRepositoryImpl(
    private val moviesClient: ApiService,
    private val movieDb: MovieDatabase
): MoviesRepository {
    override suspend fun getMovies(): Flow<UiState<List<MovieModel>>> {
        return flow {
            emit(UiState.Loading)
            val data = moviesClient.getMovies(1)
            data.onSuccess { result ->
                val mapData = result.toDomain()
                emit(UiState.Success(mapData))
            }.onError { error ->
                emit(UiState.Error(emptyList<MovieModel>(), error.name))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertMovies(dataList: List<MovieModel>) {
//        dataList.forEach { org.hariswei.cmpshowcaseproject.data ->
//            movieDb.movieDao().insertMovies(org.hariswei.cmpshowcaseproject.data.toEntity())
//        }
    }
}