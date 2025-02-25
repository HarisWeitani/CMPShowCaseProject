package org.hariswei.cmpshowcaseproject.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.hariswei.cmpshowcaseproject.data.database.MovieDatabase

fun getMovieDatabase(context: Context) : MovieDatabase {
    val dbFile = context.getDatabasePath("movie.db")
    return Room.databaseBuilder<MovieDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}