package com.example.navigation_test.page.tweet

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.navigation_test.Route
import com.example.navigation_test.entity.Tweet
import com.example.navigation_test.ui.component.AppNavigationDrawer
import com.example.navigation_test.ui.component.AppScaffold
import com.example.navigation_test.ui.theme.AppTheme

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
    AppNavigationDrawer(
        modifier = modifier,
        route = route,
        onClickFirstDrawerItem = {},
        onClickSecondDrawerItem = navigateSecondScreen,
        onClickThirdDrawerItem = navigateThirdScreen,
        onClickFourthDrawerItem = navigateFourthScreen,
        drawerState = drawerState
    ) {
        AppScaffold(
            modifier = Modifier.fillMaxSize(),
            onClickTitleIcon = {},
            drawerState = drawerState
        ) { paddingValues ->
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

@Composable
private fun TweetScreenContent(
    modifier: Modifier = Modifier,
    uiState: TweetUiState,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
    ) {
        items(
            count = uiState.tweets.size,
            key = { index -> uiState.tweets[index].name },
        ) {
            TweetItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                tweet = uiState.tweets[it],
            )
        }

    }
}

@Composable
private fun TweetItem(
    modifier: Modifier = Modifier,
    tweet: Tweet,
) {
    Card(
        modifier = modifier
    ) {
        Text(tweet.name)
        Text(tweet.content)
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
            uiState = TweetUiState(
                tweets = (1..50).map {
                    Tweet(
                        id = it,
                        name = "name$it",
                        content = "content$it"
                    )
                }
            ),
            navigateSecondScreen = {},
            navigateThirdScreen = {},
            navigateFourthScreen = {},
            drawerState = rememberDrawerState(drawerValue),
        )
    }
}