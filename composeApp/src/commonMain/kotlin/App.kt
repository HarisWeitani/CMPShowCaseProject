import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import data.database.PeopleDao
import data.database.PersonEntity
import kotlinx.serialization.Serializable
import ui.detail.DetailScreen
import ui.home.HomeRoute

@Composable
fun App(peopleDao: PeopleDao) {
    val navController = rememberNavController()

    val people by peopleDao.getAllPeople().collectAsState(emptyList())

    LaunchedEffect(true) {
        val peopleList = listOf(
            PersonEntity(name = "John"),
            PersonEntity(name = "Alice"),
            PersonEntity(name = "Philipp"),
        )
        peopleList.forEach {
            peopleDao.upsert(it)
        }
    }

    people.forEach {
        println(it.name)
    }

    MaterialTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text("Hello")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    }
                )
            }
        ) {
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = HomeScreenRoute,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(500)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(500)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(500)
                    )
                }
            ) {
                composable<HomeScreenRoute> {
                    HomeRoute(
                        navController = navController
                    )
                }
                composable<DetailScreenRoute> {
                    val args = it.toRoute<DetailScreenRoute>()
                    DetailScreen(
                        movieModel = args.data
                    )
                }
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