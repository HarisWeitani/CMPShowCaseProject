package org.hariswei.cmpshowcaseproject.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.MovieDatabase
import database.instantiateImpl
import platform.Foundation.NSHomeDirectory

fun getMovieDatabase(): MovieDatabase {
    val dbFile = NSHomeDirectory() + "/movie.db"
    return Room.databaseBuilder<MovieDatabase>(
        name = dbFile,
        factory = { MovieDatabase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(true)
        .build()
}