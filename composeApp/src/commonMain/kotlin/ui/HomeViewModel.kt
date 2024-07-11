package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import data.repository.MoviesRepository
import org.lighthousegames.logging.logging

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    fun getMovies() {
        viewModelScope.launch {
            moviesRepository.getMovies().collect {
                logging("Ajib").d { "Hello $it" }
            }
        }
    }

}