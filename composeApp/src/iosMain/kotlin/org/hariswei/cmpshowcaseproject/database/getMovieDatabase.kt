package org.hariswei.cmpshowcaseproject.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.hariswei.cmpshowcaseproject.data.database.MovieDatabase
import org.hariswei.cmpshowcaseproject.data.database.instantiateImpl
import platform.Foundation.NSHomeDirectory

fun getMovieDatabase(): MovieDatabase {
    val dbFile = NSHomeDirectory() + "/movie.db"
    return Room.databaseBuilder<MovieDatabase>(
        name = dbFile,
        factory = { MovieDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()

}