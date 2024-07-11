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
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ui.HomeViewModel

val clientModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true })
            }
        }
    }
}

val apiServiceModule = module {
    single { ApiService(get()) }
}

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}

fun appModule() = listOf(viewModelModule, repositoryModule, clientModule, apiServiceModule)

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(viewModelModule)
    }
}