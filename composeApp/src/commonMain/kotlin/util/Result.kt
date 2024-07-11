package util

interface Error

sealed class Result<out D, out E: Error> {
    data class Success<out D>(val data: D): Result<D, Nothing>()
    data class Error<out E: util.Error>(val error: E): Result<Nothing, E>()
}

inline fun <T, E: Error> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    return when(this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}
inline fun <T, E: Error> Result<T, E>.onError(action: (E) -> Unit): Result<T, E> {
    return when(this) {
        is Result.Error -> {
            action(error)
            this
        }
        is Result.Success -> this
    }
}