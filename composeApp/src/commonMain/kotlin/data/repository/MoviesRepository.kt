package data.repository

import data.database.PeopleDatabase
import data.database.PersonEntity
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
    suspend fun getPeople() : Flow<List<PersonEntity>>
    suspend fun upsertPeople(data: PersonEntity)
}

class MoviesRepositoryImpl(
    private val moviesClient: ApiService,
    private val database: PeopleDatabase
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

    override suspend fun getPeople(): Flow<List<PersonEntity>> {
        val data = database.peopleDao().getAllPeople()
        println("Ajib "+data)
        return data
    }

    override suspend fun upsertPeople(data: PersonEntity) {
        database.peopleDao().upsert(PersonEntity(name = "John"))
        database.peopleDao().upsert(PersonEntity(name = "Alice"))
        database.peopleDao().upsert(PersonEntity(name = "Phillip"))
    }
}
