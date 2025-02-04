package com.example.navigation_test.page.tweet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigation_test.entity.Tweet


@Composable
fun TweetScreenContent(
    modifier: Modifier = Modifier,
    uiState: TweetUiState,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
    ) {
        items(
            count = uiState.tweets.size,
            key = { index -> uiState.tweets[index].name },
        ) {
            TweetItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                tweet = uiState.tweets[it],
            )
        }

    }
}


@Preview
@Composable
fun TweetScreenContentPreview() {
    TweetScreenContent(
        uiState = TweetUiState(tweets = (1..50).map {
            Tweet(
                id = it, name = "name$it", content = "content$it"
            )
        })
    )
}