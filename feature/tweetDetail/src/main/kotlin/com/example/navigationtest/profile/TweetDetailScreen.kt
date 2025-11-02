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
    uiState: LoadingState<TweetDetailUiState>,
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
            when (uiState) {
                LoadingState.Failure -> Text("Failure")
                LoadingState.Loading -> CircularProgressIndicator()
                is LoadingState.Success -> Text(uiState.data.tweet.toString())
            }
        }
    }
}

private class UiStatePreviewParameter : PreviewParameterProvider<LoadingState<TweetDetailUiState>> {
    override val values: Sequence<LoadingState<TweetDetailUiState>>
        get() = sequenceOf(
            LoadingState.Loading,
            LoadingState.Failure,
            LoadingState.Success(TweetDetailUiState(EntityFaker.fakeTweet())),
        )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TweetScreenPreview(
    @PreviewParameter(UiStatePreviewParameter::class) uiState: LoadingState<TweetDetailUiState>,
) {
    AppTheme {
        TweetDetailScreen(uiState = uiState)
    }
}
