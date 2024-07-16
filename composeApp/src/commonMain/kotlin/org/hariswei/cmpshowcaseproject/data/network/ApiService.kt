package org.hariswei.cmpshowcaseproject.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import org.hariswei.cmpshowcaseproject.data.model.MoviesResponse
import org.hariswei.cmpshowcaseproject.getPlatform
import org.hariswei.cmpshowcaseproject.util.NetworkError
import org.hariswei.cmpshowcaseproject.util.Result

class ApiService (
    private val httpClient: HttpClient
) {
    suspend fun getMovies(page: Int): Result<MoviesResponse, NetworkError> {
        val bearerTokens = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyYTMwOTI5NzIyNWI2N2UxNWM1MjViYTY5NTIzZjM4ZCIsIm5iZiI6MTcyMDQxMTEyOC44ODc3NzksInN1YiI6IjVhNjJjZTk3OTI1MTQxMGIxYTAwNWQwYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gt9SjDt9Gmc2gVcjCO7nPIF6CCNf4FGwWISLuBnLWbg"
        val urlString = "${getPlatform().baseUrl}/movie/popular"

        val response = try {
            httpClient.get(
                urlString = urlString
            ) {
                parameter("language", "en-US")
                parameter("page", page)
                parameter("region", "INA")
                accept(ContentType.Application.Json)
                bearerAuth(bearerTokens)
            }
        } catch (e: Throwable) {
            return Result.Error(NetworkError.UNKNOWN)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val result = response.body<MoviesResponse>()
                Result.Success(result)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}