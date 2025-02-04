package com.example.navigation_test.page.home

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
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    lazyListState: LazyListState = rememberLazyListState(),
    navigateTweet: (Tweet) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
    ) {
        items(
            count = uiState.tweets.size,
            key = { index -> uiState.tweets[index].name },
        ) {
            HomeItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                tweet = uiState.tweets[it],
                onclick = navigateTweet
            )
        }

    }
}


@Preview
@Composable
private fun HomeContentPreview() {
    HomeContent(
        uiState = HomeUiState(tweets = (1..50).map {
            Tweet(
                id = it, name = "name$it", content = "content$it"
            )
        })
    )
}