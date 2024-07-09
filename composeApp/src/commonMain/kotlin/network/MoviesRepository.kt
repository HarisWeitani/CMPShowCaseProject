package network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.onError
import util.onSuccess

interface MoviesRepository {
    fun getMovies() : Flow<String>
}

class MoviesRepositoryImpl(
    private val moviesClient: ApiService,
): MoviesRepository {
    override fun getMovies(): Flow<String> {
        return flow {
            moviesClient.getMovies(1)
                .onSuccess {
                    emit(it.results[0].title)
                }
                .onError {
                    emit(it.name)
                }
        }
    }
}