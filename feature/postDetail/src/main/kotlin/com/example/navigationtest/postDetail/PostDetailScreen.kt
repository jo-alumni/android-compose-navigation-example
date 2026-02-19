package com.example.navigationtest.postDetail

import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.navigationtest.core.extension.copy
import com.example.navigationtest.core.ui.component.Comment
import com.example.navigationtest.core.ui.component.PostView
import com.example.navigationtest.core.ui.theme.AppTheme
import com.example.navigationtest.core.util.render
import com.example.navigationtest.domain.entity.Comment
import com.example.navigationtest.domain.entity.Post
import com.example.navigationtest.postDetail.contract.PostDetailState
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun TweetDetailRoot(
    viewModel: PostDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val uiState by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            else -> {}
        }
    }
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues.copy(bottom = 0.dp)),
            ) {
                PostView(
                    modifier = Modifier.fillMaxWidth(),
                    name = post.userId.toString(),
                    userId = post.title,
                    content = post.body,
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = paddingValues.copy(top = 0.dp, start = 0.dp, end = 0.dp),
                ) {
                    items(comments) { comment ->
                        Comment(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp),
                            name = comment.email,
                            userId = comment.id.toString(),
                            content = comment.body,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    if (uiState is PostDetailState.Stable.Loading && uiState.type == PostDetailState.Stable.Loading.Type.LOAD_MORE) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}

private class UiStatePreviewParameter : PreviewParameterProvider<PostDetailState> {
    private val comments = (1..5).map {
        Comment(
            postId = it,
            id = it,
            name = "name",
            email = "email",
            body = "comment body",
        )
    }
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
                comments = comments,
                page = 1,
                canLoadMore = true,
            ),
            PostDetailState.Stable.Loading(
                id = 1,
                post = Post(
                    userId = 1,
                    id = 1,
                    title = "title",
                    body = "body",
                ),
                comments = comments,
                page = 1,
                type = PostDetailState.Stable.Loading.Type.REFRESH,
                canLoadMore = true,
            ),
            PostDetailState.Stable.Loading(
                id = 1,
                post = Post(
                    userId = 1,
                    id = 1,
                    title = "title",
                    body = "body",
                ),
                comments = comments,
                page = 1,
                type = PostDetailState.Stable.Loading.Type.LOAD_MORE,
                canLoadMore = true,
            ),
            PostDetailState.Stable.Error(
                id = 1,
                post = Post(
                    userId = 1,
                    id = 1,
                    title = "title",
                    body = "body",
                ),
                comments = comments,
                page = 1,
                canLoadMore = true,
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
