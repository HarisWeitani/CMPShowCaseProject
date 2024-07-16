package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.hariswei.cmpshowcaseproject.data.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    fun getMovies(): Flow<List<MovieEntity>>

    @Insert
    suspend fun insertMovies(data: MovieEntity)
}