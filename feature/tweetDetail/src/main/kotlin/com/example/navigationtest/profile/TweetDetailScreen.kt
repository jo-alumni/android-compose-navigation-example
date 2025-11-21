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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.navigationtest.core.ui.theme.AppTheme
import com.example.navigationtest.core.util.render
import com.example.navigationtest.domain.entity.Tweet
import com.example.navigationtest.profile.contract.TweetDetailState

@Composable
internal fun TweetDetailRoot(
    viewModel: TweetDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TweetDetailScreen(uiState = uiState, navigateBack = navigateBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TweetDetailScreen(
    modifier: Modifier = Modifier,
    uiState: TweetDetailState,
    navigateBack: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
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
        uiState.render<TweetDetailState.Error> {
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

        uiState.render<TweetDetailState.Loading> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        uiState.render<TweetDetailState.Success> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                Text("$tweet")
            }
        }
    }
}

private class UiStatePreviewParameter : PreviewParameterProvider<TweetDetailState> {
    override val values: Sequence<TweetDetailState>
        get() = sequenceOf(
            TweetDetailState.Loading(id = "1"),
            TweetDetailState.Success(id = "1", tweet = Tweet.fake()),
            TweetDetailState.Error(id = "1"),
        )
}

@Preview(showBackground = true)
@Composable
private fun TweetScreenPreview(
    @PreviewParameter(UiStatePreviewParameter::class) uiState: TweetDetailState,
) {
    AppTheme {
        TweetDetailScreen(uiState = uiState)
    }
}
