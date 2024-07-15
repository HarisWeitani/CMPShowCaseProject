package org.hariswei.cmpshowcaseproject

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import ui.home.HomeScreen
import ui.home.HomeScreenState
import ui.home.NewsItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        state = HomeScreenState()
    )
}

@Preview
@Composable
private fun NewsItemPreview() {
    NewsItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        title = "Hello World",
        voteAverage = 5,
        imageUrl = "http://image.tmdb.org/t/p/w185/iADOJ8Zymht2JPMoy3R7xceZprc.jpg"
    )
}