package com.example.navigation_test.page.tweet

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
import com.example.navigation_test.Route
import com.example.navigation_test.entity.Tweet
import com.example.navigation_test.ui.component.AppNavigationDrawer
import com.example.navigation_test.ui.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TweetScreen(
    modifier: Modifier = Modifier,
    route: Route,
    uiState: TweetUiState,
    navigateSecondScreen: () -> Unit,
    navigateThirdScreen: () -> Unit,
    navigateFourthScreen: () -> Unit,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    AppNavigationDrawer(
        modifier = modifier,
        route = route,
        onClickProfileDrawerItem = navigateSecondScreen,
        onClickThirdDrawerItem = navigateThirdScreen,
        onClickFourthDrawerItem = navigateFourthScreen,
        drawerState = drawerState
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            CenterAlignedTopAppBar(title = {
                IconButton(onClick = { scope.launch { lazyListState.animateScrollToItem(0) } }) {
                    Icon(Icons.Default.Build, contentDescription = null)
                }
            }, navigationIcon = {
                IconButton(onClick = { scope.launch { if (drawerState.isClosed) drawerState.open() else drawerState.close() } }) {
                    Icon(Icons.Default.Menu, contentDescription = null)
                }
            })
        }, floatingActionButton = {
            FloatingActionButton(
                shape = FloatingActionButtonDefaults.shape,
                onClick = { /*TODO*/ },
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }) { paddingValues ->
            TweetScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                uiState = uiState,
                lazyListState = lazyListState,
            )
        }
    }
}

private class DrawerValueParameterProvider : PreviewParameterProvider<DrawerValue> {
    override val values: Sequence<DrawerValue> = sequenceOf(
        DrawerValue.Closed,
        DrawerValue.Open,
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TweetScreenClosedPreview(
    @PreviewParameter(DrawerValueParameterProvider::class) drawerValue: DrawerValue,
) {
    AppTheme {
        TweetScreen(
            route = Route.Tweet,
            uiState = TweetUiState(tweets = (1..50).map {
                Tweet(
                    id = it, name = "name$it", content = "content$it"
                )
            }),
            navigateSecondScreen = {},
            navigateThirdScreen = {},
            navigateFourthScreen = {},
            drawerState = rememberDrawerState(drawerValue),
        )
    }
}