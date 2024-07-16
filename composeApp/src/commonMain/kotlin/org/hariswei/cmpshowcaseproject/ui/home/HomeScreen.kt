package org.hariswei.cmpshowcaseproject.ui.home

import org.hariswei.cmpshowcaseproject.DetailScreenRoute
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.hariswei.cmpshowcaseproject.domain.model.MovieModel
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

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

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeRoute(
    navController: NavController
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMovies()
        viewModel.testDb()
    }

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

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    title: String = "",
    voteAverage: Int = 0,
    imageUrl: String = "",
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Image Poster",
                onLoading = {
                    println("OnLoading")
                },
                onError = {
                    println("onError ${it.result.throwable.message}")
                },
                onSuccess = {
                    println("OnSuccess")
                }
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp),
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow
                    )
                    Text(
                        text = "$voteAverage / 10",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}