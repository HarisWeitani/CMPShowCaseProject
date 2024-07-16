package database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.hariswei.cmpshowcaseproject.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase(), DB {

    abstract fun movieDao(): MovieDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB {
    fun clearAllTables() {}
}