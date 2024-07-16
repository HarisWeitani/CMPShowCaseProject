package org.hariswei.cmpshowcaseproject.di

import database.MovieDatabase
import org.hariswei.cmpshowcaseproject.database.getMovieDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<MovieDatabase> { getMovieDatabase() }
}