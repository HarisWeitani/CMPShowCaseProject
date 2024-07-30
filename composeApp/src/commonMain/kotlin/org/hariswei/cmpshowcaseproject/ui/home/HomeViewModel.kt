package org.hariswei.cmpshowcaseproject.ui.home

import androidx.compose.ui.util.fastRoundToInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.hariswei.cmpshowcaseproject.domain.usecase.MoviesUseCase
import org.hariswei.cmpshowcaseproject.ui.home.HomeScreenState.Data
import org.hariswei.cmpshowcaseproject.util.UiState

class HomeViewModel(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState

    init {
        onEvent(HomeScreenEvent.GetMovies)
    }

    fun onEvent(event: HomeScreenEvent) {
        when(event) {
            HomeScreenEvent.GetMovies -> getMovies()
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            moviesUseCase.getMovies().collect { result ->
                when (result) {
                    UiState.Loading -> _uiState.value = HomeScreenState(isLoading = true)
                    is UiState.Error -> _uiState.value = HomeScreenState(
                        isLoading = false,
                        errMsg = result.message,
                        dataList = listOf()
                    )
                    is UiState.Success -> {
                        val dataList = result.data?.map {
                            Data(
                                id = it.id,
                                title = it.title,
                                voteAverage = it.voteAverage.fastRoundToInt(),
                                imageUrl = it.posterPath,
                                model = it
                            )
                        } ?: listOf()

                        _uiState.value = HomeScreenState(
                            isLoading = false,
                            errMsg = "",
                            dataList = dataList,
                        )
                    }
                }
            }
        }
    }

}