package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDao

}
