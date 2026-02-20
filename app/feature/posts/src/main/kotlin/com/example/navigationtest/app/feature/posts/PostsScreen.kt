package com.example.navigationtest.app.feature.posts

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.navigation_test.app.feature.posts.R
import com.example.navigationtest.app.core.extension.OnBottomReached
import com.example.navigationtest.app.core.extension.copy
import com.example.navigationtest.app.core.ui.component.AppNavigationDrawer
import com.example.navigationtest.app.core.ui.component.PostView
import com.example.navigationtest.app.core.ui.theme.AppTheme
import com.example.navigationtest.app.core.util.render
import com.example.navigationtest.app.feature.posts.contract.PostsEvent
import com.example.navigationtest.app.feature.posts.contract.PostsState
import com.example.navigationtest.domain.entity.Post
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun PostsRoot(
    drawerState: DrawerState,
    navigatePostDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PostsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    viewModel.collectSideEffect {
        when (it) {
            is PostsEvent.ShowSnackbar -> when (it) {
                PostsEvent.ShowSnackbar.Success -> snackbarHostState.showSnackbar(context.getString(R.string.success))
                PostsEvent.ShowSnackbar.Error -> snackbarHostState.showSnackbar(context.getString(R.string.error))
            }
        }
    }

    PostScreen(
        modifier = modifier,
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        navigatePostDetail = navigatePostDetail,
        onRefresh = viewModel::refresh,
        onLoadMore = viewModel::loadMore,
        drawerState = drawerState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostScreen(
    uiState: PostsState,
    drawerState: DrawerState,
    snackbarHostState: SnackbarHostState,
    navigatePostDetail: (Int) -> Unit,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListStates = PostsTab.entries.map { rememberLazyListState() }
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { PostsTab.entries.size }

    lazyListStates.forEachIndexed { index, state ->
        if (pagerState.currentPage == index) {
            state.OnBottomReached { onLoadMore() }
        }
    }

    AppNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        IconButton(onClick = { scope.launch { lazyListStates[pagerState.currentPage].animateScrollToItem(0) } }) {
                            Icon(Icons.Default.Build, contentDescription = null)
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { if (drawerState.isClosed) drawerState.open() else drawerState.close() } }) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    },
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    shape = FloatingActionButtonDefaults.shape,
                    onClick = { /*TODO*/ },
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues.copy(bottom = 0.dp)),
            ) {
                SecondaryTabRow(selectedTabIndex = pagerState.currentPage) {
                    PostsTab.entries.forEach { tab ->
                        Tab(
                            selected = pagerState.currentPage == tab.ordinal,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        page = tab.ordinal,
                                        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                                    )
                                }
                            },
                            text = { Text(text = tab.title) },
                        )
                    }
                }
                HorizontalPager(state = pagerState) { page ->
                    PullToRefreshBox(
                        isRefreshing = uiState is PostsState.Loading && uiState.type == PostsState.Loading.Type.REFRESH,
                        onRefresh = onRefresh,
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = lazyListStates[page],
                            contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding()),
                        ) {
                            items(items = uiState.posts, key = { it.id }) {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    PostView(
                                        modifier = Modifier.fillMaxWidth(),
                                        name = it.userId.toString(),
                                        userId = it.title,
                                        content = it.body,
                                        onClickPost = { navigatePostDetail(it.id) },
                                    )
                                    HorizontalDivider()
                                }
                            }
                            if (uiState is PostsState.Loading && uiState.type == PostsState.Loading.Type.LOAD_MORE) {
                                item {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                            .wrapContentWidth(),
                                    )
                                }
                            }
                        }
                    }
                }

                uiState.render<PostsState.Error> {
                    AlertDialog(
                        onDismissRequest = onRefresh,
                        confirmButton = {
                            Button(onClick = onRefresh) {
                                Text(text = "Retry")
                            }
                        },
                        dismissButton = {
                            Button(onClick = {}) {
                                Text(text = "Cancel")
                            }
                        },
                        text = { Text(text = cause?.message ?: "Unknown error") },
                    )
                }
            }
        }
    }
}

private class UiStateParameterProvider : PreviewParameterProvider<PostsState> {
    private val tweets = (1..5).map {
        Post(
            id = it,
            userId = it,
            title = "title_$it",
            body = "body_$it",
        )
    }

    override val values: Sequence<PostsState> = sequenceOf(
        PostsState.Stable(
            posts = tweets,
            page = 1,
            canLoadMore = true,
        ),
        PostsState.Loading(
            posts = listOf(), type = PostsState.Loading.Type.REFRESH,
            page = 1,
            canLoadMore = true,
        ),
        PostsState.Loading(
            posts = tweets, type = PostsState.Loading.Type.REFRESH,
            page = 1,
            canLoadMore = true,
        ),
        PostsState.Loading(
            posts = tweets, type = PostsState.Loading.Type.LOAD_MORE,
            page = 1,
            canLoadMore = true,
        ),
        PostsState.Error(
            posts = listOf(), cause = Exception("error"),
            page = 1,
            canLoadMore = true,
        ),
        PostsState.Error(
            posts = tweets, cause = Exception("error"),
            page = 1,
            canLoadMore = true,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun PostScreenPreview(
    @PreviewParameter(UiStateParameterProvider::class) uiState: PostsState,
) {
    AppTheme {
        PostScreen(
            uiState = uiState,
            drawerState = rememberDrawerState(DrawerValue.Closed),
            snackbarHostState = remember { SnackbarHostState() },
            navigatePostDetail = {},
            onRefresh = {},
            onLoadMore = {},
        )
    }
}
