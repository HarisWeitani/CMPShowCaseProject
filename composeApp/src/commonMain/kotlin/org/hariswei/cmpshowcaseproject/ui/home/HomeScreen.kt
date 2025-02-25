package org.hariswei.cmpshowcaseproject.ui.home

import org.hariswei.cmpshowcaseproject.ui.app.DetailScreenRoute
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.hariswei.cmpshowcaseproject.domain.model.MovieModel
import kotlinx.coroutines.delay
import org.hariswei.cmpshowcaseproject.ui.home.item.NewsItem
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeRoute(
    navController: NavController
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        state = uiState,
        onItemClick = {
            navController.navigate(DetailScreenRoute("it"))
        }
    )
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onItemClick: (data : MovieModel) -> Unit = {}
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        isVisible = true
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(
            count = state.dataList.size,
            key = { state.dataList[it].id },
        ) {
            val data = state.dataList[it]
            NewsItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        onItemClick(state.dataList[it].model)
                    },
                title = data.title,
                voteAverage = data.voteAverage,
                imageUrl = data.imageUrl
            )
        }
    }
}
