package data.repository

import data.network.ApiService
import domain.model.MovieModel
import domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import util.UiState
import util.onError
import util.onSuccess

interface MoviesRepository {
    suspend fun getMovies() : Flow<UiState<List<MovieModel>>>
}

class MoviesRepositoryImpl(
    private val moviesClient: ApiService,
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
}