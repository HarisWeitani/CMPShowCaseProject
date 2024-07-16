package org.hariswei.cmpshowcaseproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.hariswei.cmpshowcaseproject.data.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase(), DB{

    abstract fun movieDao(): MovieDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

//Class 'MovieDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}