package di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import data.network.ApiService
import data.repository.MoviesRepository
import data.repository.MoviesRepositoryImpl
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ui.home.HomeViewModel

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

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

expect val databaseModule : Module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}

fun appModule() = listOf(viewModelModule, repositoryModule, networkModule)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(viewModelModule, repositoryModule, networkModule, databaseModule)
    }
}