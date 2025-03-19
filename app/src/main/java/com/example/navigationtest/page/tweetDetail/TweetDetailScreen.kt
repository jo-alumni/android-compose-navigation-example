package com.example.navigationtest.page.tweetDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigationtest.entity.Tweet
import com.example.navigationtest.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TweetDetailScreen(
    modifier: Modifier = Modifier,
    tweet: Tweet?,
    navigateBack: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack,
                    ) {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
                    }
                },
            )
        },
    ) { paddingValues ->
        Box(modifier = modifier.padding(paddingValues)) {
            Text(text = tweet.toString())
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TweetScreenPreview() {
    AppTheme {
        TweetDetailScreen(
            tweet =
                Tweet(
                    id = 1,
                    name = "name",
                    content = "content",
                ),
        )
    }
}
