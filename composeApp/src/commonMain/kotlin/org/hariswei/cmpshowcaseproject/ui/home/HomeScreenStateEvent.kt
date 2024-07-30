package org.hariswei.cmpshowcaseproject.ui.home

import org.hariswei.cmpshowcaseproject.domain.model.MovieModel

data class HomeScreenState(
    val isLoading: Boolean = false,
    val errMsg: String = "",
    val dataList: List<Data> = listOf(),
) {
    data class Data(
        val id: Int,
        val title: String,
        val voteAverage: Int,
        val imageUrl: String,
        val model: MovieModel
    )
}

sealed class HomeScreenEvent {
    data object GetMovies : HomeScreenEvent()
}