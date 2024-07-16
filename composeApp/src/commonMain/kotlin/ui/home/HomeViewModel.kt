package ui.home

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.util.fastRoundToInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.database.PersonEntity
import kotlinx.coroutines.launch
import data.repository.MoviesRepository
import domain.model.MovieModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ui.home.HomeScreenState.Data
import util.UiState
import kotlin.math.roundToInt

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState

    fun getMovies() {
        viewModelScope.launch {
            moviesRepository.getMovies().collect { result ->
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

    fun testDb() {
        viewModelScope.launch{
            moviesRepository.upsertPeople(PersonEntity(name = "Ajib"))
            moviesRepository.getPeople()
        }
    }

}