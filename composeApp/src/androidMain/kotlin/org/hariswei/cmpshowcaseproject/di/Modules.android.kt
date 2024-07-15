package org.hariswei.cmpshowcaseproject.di

import data.database.MovieDatabase
import org.hariswei.cmpshowcaseproject.database.getMovieDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<MovieDatabase> { getMovieDatabase(get()) }
}