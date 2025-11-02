package com.example.navigationtest.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.navigationtest.core.ui.component.AppNavigationDrawer
import com.example.navigationtest.core.ui.theme.AppTheme
import com.example.navigationtest.core.util.LoadingState
import com.example.navigationtest.domain.entity.Profile
import com.example.navigationtest.domain.entity.Tweet
import kotlinx.coroutines.launch

@Composable
fun HomeRoot(
    viewModel: HomeViewModel,
    drawerState: DrawerState,
    navigateProfile: (Profile) -> Unit,
    navigateTweet: (Tweet) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        navigateProfile = navigateProfile,
        navigateTweet = navigateTweet,
        drawerState = drawerState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    drawerState: DrawerState,
    navigateProfile: (Profile) -> Unit,
    navigateTweet: (Tweet) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    AppNavigationDrawer(
        modifier = modifier,
        navigateProfile = {
            navigateProfile(
                Profile(
                    id = "john_doe",
                    name = "John Doe",
                    description = "John Doe's description",
                ),
            )
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        IconButton(onClick = { scope.launch { lazyListState.animateScrollToItem(0) } }) {
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
        ) { paddingValues ->
            when (uiState.tweets) {
                LoadingState.Failure -> {
                    // TODO: implementation
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    ) {
                        Text(modifier = Modifier.align(Alignment.Center), text = "failed")
                    }
                }

                LoadingState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    ) {
                        CircularProgressIndicator(modifier.align(Alignment.Center))
                    }
                }

                is LoadingState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        state = lazyListState,
                    ) {
                        items(
                            count = uiState.tweets.data.size,
                            key = { index -> uiState.tweets.data[index].id },
                        ) {
                            TweetItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                tweet = uiState.tweets.data[it],
                                onClickTweet = navigateTweet,
                                onClickProfile = navigateProfile,
                            )
                        }
                    }
                }
            }
        }
    }
}

private class UiStateParameterProvider : PreviewParameterProvider<HomeUiState> {
    override val values: Sequence<HomeUiState> = sequenceOf(
        HomeUiState(LoadingState.Loading),
        HomeUiState(
            LoadingState.Success(
                (1..50).map {
                    Tweet(
                        id = it,
                        content = "content$it",
                        postUser = Profile(
                            id = "user_id_$it",
                            name = "user_name_$it",
                            description = "description_$it",
                        ),
                    )
                },
            ),
        ),
        HomeUiState(LoadingState.Failure),
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(UiStateParameterProvider::class) uiState: HomeUiState,
) {
    AppTheme {
        HomeScreen(
            uiState = uiState,
            drawerState = rememberDrawerState(DrawerValue.Closed),
            navigateProfile = {},
            navigateTweet = {},
        )
    }
}
