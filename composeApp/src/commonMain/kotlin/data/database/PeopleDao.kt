package data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {

    @Upsert
    suspend fun upsert(person: PersonEntity)

    @Query("SELECT * FROM personEntity")
    fun getAllPeople(): Flow<List<PersonEntity>>
}
