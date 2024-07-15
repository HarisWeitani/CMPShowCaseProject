package data.database

import androidx.room.Dao
import androidx.room.Query
import data.model.MovieEntity
import domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    fun getMovies(): Flow<List<MovieEntity>>

    suspend fun insertMovies(data: List<MovieEntity>)
}