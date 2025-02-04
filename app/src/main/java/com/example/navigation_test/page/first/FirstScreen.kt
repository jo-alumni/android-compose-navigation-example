package com.example.navigation_test.page.first

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.navigation_test.Route
import com.example.navigation_test.ui.component.AppNavigationDrawer
import com.example.navigation_test.ui.component.AppScaffold
import com.example.navigation_test.ui.theme.AppTheme

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    route: Route,
    uiState: FirstUiState,
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
            FirstScreenContent(
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
private fun FirstScreenContent(
    modifier: Modifier = Modifier,
    uiState: FirstUiState,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
    ) {

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
fun FirstScreenClosedPreview(
    @PreviewParameter(DrawerValueParameterProvider::class) drawerValue: DrawerValue,
) {
    AppTheme {
        FirstScreen(
            route = Route.First,
            uiState = FirstUiState(),
            navigateSecondScreen = {},
            navigateThirdScreen = {},
            navigateFourthScreen = {},
            drawerState = rememberDrawerState(drawerValue),
        )
    }
}