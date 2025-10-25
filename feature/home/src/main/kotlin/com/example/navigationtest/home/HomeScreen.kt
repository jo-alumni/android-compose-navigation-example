package com.example.navigationtest.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.navigationtest.core.entity.Tweet
import com.example.navigationtest.core.ui.component.AppNavigationDrawer
import com.example.navigationtest.core.ui.theme.AppTheme
import com.example.navigationtest.core.util.LoadingState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    navigateProfileMine: () -> Unit = {},
    navigateTweet: (Tweet) -> Unit = {},
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    AppNavigationDrawer(
        modifier = modifier,
        navigateProfile = navigateProfileMine,
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
            HomeContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                uiState = uiState,
                lazyListState = lazyListState,
                navigateTweet = navigateTweet,
            )
        }
    }
}

private class HomeUiStateParameterProvider : PreviewParameterProvider<HomeUiState> {
    override val values: Sequence<HomeUiState> =
        sequenceOf(
            HomeUiState(LoadingState.Loading),
            HomeUiState(
                LoadingState.Success(
                    (1..50).map { Tweet(id = it, name = "name$it", content = "content$it") },
                ),
            ),
            HomeUiState(LoadingState.Failure),
        )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomeUiStateParameterProvider::class) uiState: HomeUiState,
) {
    AppTheme {
        HomeScreen(uiState = uiState)
    }
}
