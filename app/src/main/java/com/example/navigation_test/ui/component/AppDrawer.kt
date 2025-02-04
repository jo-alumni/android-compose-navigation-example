package com.example.navigation_test.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.navigation_test.Route
import com.example.navigation_test.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun AppNavigationDrawer(
    modifier: Modifier = Modifier,
    route: Route,
    onClickFirstDrawerItem: () -> Unit,
    onClickSecondDrawerItem: () -> Unit,
    onClickThirdDrawerItem: () -> Unit,
    onClickFourthDrawerItem: () -> Unit,
    drawerState: DrawerState,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Drawer Title",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )

                    HorizontalDivider()

                    Text(
                        "Section 1",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text("First Screen") },
                        selected = route == Route.Tweet,
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                        onClick = {
                            onClickFirstDrawerItem()
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Second Screen") },
                        selected = route == Route.Profile,
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                        onClick = {
                            onClickSecondDrawerItem()
                            scope.launch { drawerState.close() }
                        }
                    )

                    HorizontalDivider()

                    Text(
                        "Section 2",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text("Third Screen") },
                        selected = route == Route.Third,
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                        onClick = {
                            onClickThirdDrawerItem()
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Fourth Screen") },
                        selected = route == Route.Fourth,
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                        onClick = {
                            onClickFourthDrawerItem()
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        }
    ) {
        content()
    }
}

private class RouteParameterProvider : PreviewParameterProvider<Route> {
    override val values: Sequence<Route> = sequenceOf(
        Route.Tweet,
        Route.Profile,
        Route.Third,
        Route.Fourth,
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppNavigationDrawerPreview(
    @PreviewParameter(RouteParameterProvider::class) route: Route,
) {
    AppTheme {
        AppNavigationDrawer(
            route = route,
            onClickFirstDrawerItem = {},
            onClickSecondDrawerItem = {},
            onClickThirdDrawerItem = {},
            onClickFourthDrawerItem = {},
            drawerState = rememberDrawerState(DrawerValue.Open)
        ) {

        }
    }
}