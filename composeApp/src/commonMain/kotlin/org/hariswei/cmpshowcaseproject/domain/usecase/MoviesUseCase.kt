package org.hariswei.cmpshowcaseproject.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.hariswei.cmpshowcaseproject.data.model.toEntity
import org.hariswei.cmpshowcaseproject.data.repository.MoviesRepository
import org.hariswei.cmpshowcaseproject.domain.model.MovieModel
import org.hariswei.cmpshowcaseproject.domain.model.toModel
import org.hariswei.cmpshowcaseproject.util.UiState
import org.hariswei.cmpshowcaseproject.util.onError
import org.hariswei.cmpshowcaseproject.util.onSuccess


interface MoviesUseCase {
    suspend fun getMovies() : Flow<UiState<List<MovieModel>>>
}

class MoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : MoviesUseCase {
    override suspend fun getMovies(): Flow<UiState<List<MovieModel>>> {
        return flow {
            emit(UiState.Loading)
            val data = moviesRepository.getMovies()
            data.onSuccess { result ->
                val mapData = result.toModel()
                moviesRepository.upsertMovies(result.toEntity())
                emit(UiState.Success(mapData))
            }.onError { error ->
                emit(UiState.Error(emptyList<MovieModel>(), error.name))
            }
        }.flowOn(Dispatchers.IO)
    }

}