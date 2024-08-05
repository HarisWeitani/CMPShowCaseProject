package org.hariswei.cmpshowcaseproject.ui.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.hariswei.cmpshowcaseproject.ui.app.component.SortDropDownMenu
import org.hariswei.cmpshowcaseproject.ui.app.component.SortType
import org.hariswei.cmpshowcaseproject.ui.detail.DetailScreen
import org.hariswei.cmpshowcaseproject.ui.home.HomeRoute
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {
    val navController = rememberNavController()

    val viewModel = koinViewModel<AppbarViewModel>()
    val backButtonState by viewModel.backNavState.collectAsState()
    val sortDropDownState by viewModel.sortDropDownState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if(backButtonState) {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
                                },
                                content = {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                                }
                            )
                        }
                        Text("Popular Movies")
                    }
                },
                actions = {
                    if (!backButtonState) {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(AppbarScreenEvent.IsShowSortDropDown)
                            },
                            content = {
                                Icon(Icons.Filled.Menu, contentDescription = null)
                            }
                        )
                    }
                    SortDropDownMenu(
                        isDisplayed = sortDropDownState,
                        selectedType = SortType.RECENT,
                        onDismiss = {
                            viewModel.onEvent(AppbarScreenEvent.IsShowSortDropDown)
                        },
                        onSelectType = {
                            viewModel.onEvent(AppbarScreenEvent.IsShowSortDropDown)
                        },
                    )
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp
            )
        }
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = HomeScreenRoute,
        ) {
            composable<HomeScreenRoute> {
                viewModel.onEvent(AppbarScreenEvent.IsShowBackButton(false))
                HomeRoute(
                    navController = navController
                )
            }
            composable<DetailScreenRoute> {
                viewModel.onEvent(AppbarScreenEvent.IsShowBackButton(true))
                val args = it.toRoute<DetailScreenRoute>()
                DetailScreen(
                    movieModel = args.data
                )
            }
        }
    }
}

@Serializable
object HomeScreenRoute

@Serializable
data class DetailScreenRoute(
    val data: String
)