package ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import data.repository.MoviesRepository
import domain.model.MovieModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import util.UiState

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<MovieModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<MovieModel>>> = _uiState

    fun getMovies() {
        viewModelScope.launch {
            moviesRepository.getMovies().collect { result ->
                when(result) {
                    UiState.Loading -> _uiState.value = UiState.Loading
                    is UiState.Error -> _uiState.value = UiState.Error(result.data, result.message)
                    is UiState.Success -> {
                        _uiState.value = UiState.Success(result.data)
                    }
                }
            }
        }
    }

}