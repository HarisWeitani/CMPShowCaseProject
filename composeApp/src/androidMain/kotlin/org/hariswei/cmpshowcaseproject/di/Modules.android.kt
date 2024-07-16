package org.hariswei.cmpshowcaseproject.di

import org.hariswei.cmpshowcaseproject.data.database.MovieDatabase
import org.hariswei.cmpshowcaseproject.database.getMovieDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseModule: Module = module {
    single<MovieDatabase> { getMovieDatabase(get()) }
}