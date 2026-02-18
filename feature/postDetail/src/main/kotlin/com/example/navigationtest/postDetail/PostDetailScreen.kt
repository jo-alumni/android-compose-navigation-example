package com.example.navigationtest.postDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import com.example.navigationtest.core.ui.component.CommentView
import com.example.navigationtest.core.ui.component.PostView
import com.example.navigationtest.core.ui.theme.AppTheme
import com.example.navigationtest.core.util.render
import com.example.navigationtest.domain.entity.Post
import com.example.navigationtest.postDetail.contract.PostDetailState

@Composable
internal fun TweetDetailRoot(
    viewModel: PostDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TweetDetailScreen(uiState = uiState, navigateBack = navigateBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TweetDetailScreen(
    modifier: Modifier = Modifier,
    uiState: PostDetailState,
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
        uiState.render<PostDetailState.Error> {
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

        uiState.render<PostDetailState.Loading> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        uiState.render<PostDetailState.Stable> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = paddingValues,
            ) {
                item {
                    PostView(
                        modifier = Modifier.fillMaxWidth(),
                        name = post.userId.toString(),
                        userId = post.title,
                        content = post.body,
                    )
                    HorizontalDivider()
                }

                items(comments) { comment ->
                    CommentView(
                        modifier = Modifier.fillMaxWidth(),
                        name = comment.email,
                        userId = comment.id.toString(),
                        content = comment.body,
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

private class UiStatePreviewParameter : PreviewParameterProvider<PostDetailState> {
    override val values: Sequence<PostDetailState>
        get() = sequenceOf(
            PostDetailState.Loading(id = 1),
            PostDetailState.Stable.Initial(
                id = 1,
                post = Post(
                    userId = 1,
                    id = 1,
                    title = "title",
                    body = "body",
                ),
                comments = emptyList(),
                page = 1,
            ),
            PostDetailState.Stable.Loading(
                id = 1,
                post = Post(
                    userId = 1,
                    id = 1,
                    title = "title",
                    body = "body",
                ),
                comments = emptyList(),
                page = 1,
                type = PostDetailState.Stable.Loading.Type.REFRESH,
            ),
            PostDetailState.Stable.Loading(
                id = 1,
                post = Post(
                    userId = 1,
                    id = 1,
                    title = "title",
                    body = "body",
                ),
                comments = emptyList(),
                page = 1,
                type = PostDetailState.Stable.Loading.Type.LOAD_MORE,
            ),
            PostDetailState.Stable.Error(
                id = 1,
                post = Post(
                    userId = 1,
                    id = 1,
                    title = "title",
                    body = "body",
                ),
                comments = emptyList(),
                page = 1,
            ),
            PostDetailState.Error(id = 1),
        )
}

@Preview(showBackground = true)
@Composable
private fun TweetScreenPreview(
    @PreviewParameter(UiStatePreviewParameter::class) uiState: PostDetailState,
) {
    AppTheme {
        TweetDetailScreen(uiState = uiState)
    }
}
