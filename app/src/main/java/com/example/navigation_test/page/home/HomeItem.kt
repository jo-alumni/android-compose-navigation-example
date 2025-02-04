package com.example.navigation_test.page.home

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigation_test.entity.Tweet


@Composable
fun TweetItem(
    modifier: Modifier = Modifier,
    tweet: Tweet,
) {
    Card(
        modifier = modifier
    ) {
        Text(tweet.name)
        Text(tweet.content)
    }
}

@Preview(widthDp = 300)
@Composable
fun TweetItemPreview() {
    TweetItem(
        tweet = Tweet(
            id = 1,
            name = "name",
            content = "content"
        )
    )
}