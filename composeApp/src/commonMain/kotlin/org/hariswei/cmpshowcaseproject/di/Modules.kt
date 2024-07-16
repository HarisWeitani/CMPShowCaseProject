package org.hariswei.cmpshowcaseproject.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.hariswei.cmpshowcaseproject.data.network.ApiService
import org.hariswei.cmpshowcaseproject.data.repository.MoviesRepository
import org.hariswei.cmpshowcaseproject.data.repository.MoviesRepositoryImpl
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.hariswei.cmpshowcaseproject.ui.home.HomeViewModel

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true })
            }
        }
    }
    single { ApiService(get()) }
}

expect val platformModule: Module

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}

fun appModule() =
    listOf(viewModelModule, repositoryModule, networkModule, platformModule)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule, repositoryModule, networkModule, platformModule
        )
    }