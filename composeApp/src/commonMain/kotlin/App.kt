import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import di.appModule
import org.koin.compose.KoinApplication
import ui.detail.DetailScreen
import ui.home.HomeScreen

@Composable
fun App() {
    val navController = rememberNavController()

    KoinApplication(
        application = {
            modules(appModule())
        }
    ) {
        MaterialTheme {
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = "home",
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
                composable(route = "home") {
                    HomeScreen(
                        navController = navController
                    )
                }
                composable(route = "detail") {
                    DetailScreen()
                }
            }
        }
    }
}