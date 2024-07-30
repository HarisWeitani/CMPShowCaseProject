package org.hariswei.cmpshowcaseproject.ui.home.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage


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