package com.example.navigationtest.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.navigationtest.core.ui.component.AppNavigationDrawer
import com.example.navigationtest.core.ui.component.TweetView
import com.example.navigationtest.core.ui.theme.AppTheme
import com.example.navigationtest.core.util.render
import com.example.navigationtest.domain.entity.Profile
import com.example.navigationtest.domain.entity.Tweet
import com.example.navigationtest.home.contract.HomeUiState
import kotlinx.coroutines.launch

@Composable
internal fun HomeRoot(
    drawerState: DrawerState,
    navigateProfile: (String) -> Unit,
    navigateTweet: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // handle events
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                else -> Unit
            }
        }
    }

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        navigateProfile = navigateProfile,
        navigateTweet = navigateTweet,
        onRefresh = viewModel::load,
        drawerState = drawerState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    drawerState: DrawerState,
    navigateProfile: (String) -> Unit,
    navigateTweet: (String) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    var selectedTab by rememberSaveable { mutableIntStateOf(HomeTab.Recommended.ordinal) }

    AppNavigationDrawer(
        modifier = modifier,
        navigateProfile = { navigateProfile("john_doe") },
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                SecondaryTabRow(selectedTabIndex = selectedTab) {
                    HomeTab.entries.forEach { tab ->
                        Tab(
                            selected = selectedTab == tab.ordinal,
                            onClick = { selectedTab = tab.ordinal },
                            text = { Text(text = tab.title) },
                        )
                    }
                }
                PullToRefreshBox(
                    isRefreshing = uiState is HomeUiState.Loading,
                    onRefresh = onRefresh,
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = lazyListState,
                    ) {
                        items(items = uiState.tweets, key = { it.id }) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                TweetView(
                                    modifier = Modifier.fillMaxWidth(),
                                    name = it.postUser.name,
                                    userId = it.postUser.id,
                                    content = it.content,
                                    onClickTweet = { navigateTweet(it.id) },
                                    onClickProfile = { navigateProfile(it.postUser.id) },
                                )
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }

            uiState.render<HomeUiState.Error> {
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

private class UiStateParameterProvider : PreviewParameterProvider<HomeUiState> {
    private val tweets = (1..50).map {
        Tweet(
            id = "id_$it",
            content = "content$it",
            postUser = Profile(
                id = "user_id_$it",
                name = "user_name_$it",
                description = "description_$it",
            ),
        )
    }

    override val values: Sequence<HomeUiState> = sequenceOf(
        HomeUiState.Success(tweets = tweets),
        HomeUiState.Loading(tweets = listOf()),
        HomeUiState.Loading(tweets = tweets),
        HomeUiState.Error(tweets = listOf(), cause = Exception("error")),
        HomeUiState.Error(tweets = tweets, cause = Exception("error")),
    )
}

@Preview(showBackground = true)
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
            onRefresh = {},
        )
    }
}
