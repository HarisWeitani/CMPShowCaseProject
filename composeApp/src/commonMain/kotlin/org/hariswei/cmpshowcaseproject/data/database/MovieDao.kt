package org.hariswei.cmpshowcaseproject.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import org.hariswei.cmpshowcaseproject.data.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    fun getMovies(): List<MovieEntity>

    @Upsert
    suspend fun upsertMovies(data: List<MovieEntity>)
}