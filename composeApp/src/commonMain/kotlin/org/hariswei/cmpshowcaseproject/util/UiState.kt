package org.hariswei.cmpshowcaseproject.util

sealed class UiState<out T> {
    data class Success<T>(val data: T?) : UiState<T>()
    data class Error<T>(val data: T? , val message: String) : UiState<T>()
    data object Loading : UiState<Nothing>()
}