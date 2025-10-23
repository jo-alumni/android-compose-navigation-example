package com.example.navigationtest.home

import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigationtest.core.ui.entity.Tweet

@Composable
fun HomeItem(
    modifier: Modifier = Modifier,
    tweet: Tweet,
    onclick: (Tweet) -> Unit = {},
) {
    Card(
        modifier = modifier.clickable { onclick(tweet) },
    ) {
        Text(tweet.name)
        Text(tweet.content)
    }
}

@Preview(widthDp = 300)
@Composable
private fun HomeItemPreview() {
    HomeItem(
        tweet =
            Tweet(
                id = 1,
                name = "name",
                content = "content",
            ),
    )
}
