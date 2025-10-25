package com.example.navigationtest.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigationtest.core.entity.Tweet
import com.example.navigationtest.core.util.LoadingState

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    lazyListState: LazyListState = rememberLazyListState(),
    navigateTweet: (Tweet) -> Unit = {},
) {
    when (uiState.tweets) {
        LoadingState.Failure -> {
            // TODO: implementation
            Box(modifier = modifier) {
                Text(modifier = Modifier.align(Alignment.Center), text = "failed")
            }
        }

        LoadingState.Loading -> {
            Box(modifier = modifier) {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
        }

        is LoadingState.Success -> {
            LazyColumn(
                modifier = modifier,
                state = lazyListState,
            ) {
                items(
                    count = uiState.tweets.data.size,
                    key = { index -> uiState.tweets.data[index].name },
                ) {
                    HomeItem(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        tweet = uiState.tweets.data[it],
                        onclick = navigateTweet,
                    )
                }
            }
        }
    }
}
