package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import network.MoviesRepository

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    fun getMovies() {
        viewModelScope.launch {
            moviesRepository.getMovies().collect {
                println(it)
            }
        }
    }

}