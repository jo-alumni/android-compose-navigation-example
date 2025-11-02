package com.example.navigationtest.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.navigationtest.core.ui.theme.AppTheme
import com.example.navigationtest.core.util.LoadingState
import com.example.navigationtest.domain.entity.EntityFaker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TweetDetailScreen(
    modifier: Modifier = Modifier,
    uiState: TweetDetailUiState,
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
            when (uiState.tweet) {
                LoadingState.Failure -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Failure",
                        )
                    }
                }

                LoadingState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    ) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is LoadingState.Success -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    ) {
                        Text("${uiState.tweet}")
                    }
                }
            }
        }
    }
}

private class UiStatePreviewParameter : PreviewParameterProvider<TweetDetailUiState> {
    override val values: Sequence<TweetDetailUiState>
        get() = sequenceOf(
            TweetDetailUiState(LoadingState.Loading),
            TweetDetailUiState(LoadingState.Success((EntityFaker.fakeTweet()))),
            TweetDetailUiState(LoadingState.Failure),
        )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TweetScreenPreview(
    @PreviewParameter(UiStatePreviewParameter::class) uiState: TweetDetailUiState,
) {
    AppTheme {
        TweetDetailScreen(uiState = uiState)
    }
}
