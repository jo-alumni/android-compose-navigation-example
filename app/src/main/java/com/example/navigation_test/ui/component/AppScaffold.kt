package com.example.navigation_test.ui.component

import androidx.compose.foundation.layout.PaddingValues
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
import com.example.navigation_test.ui.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    onClickTitleIcon: () -> Unit,
    onClickFloatingActionButton: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    IconButton(
                        onClick = onClickTitleIcon,
                    ) {
                        Icon(Icons.Default.Build, contentDescription = null)
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { scope.launch { if (drawerState.isClosed) drawerState.open() else drawerState.close() } }
                    ) {
                        Icon(Icons.Default.Menu, contentDescription = null)
                    }
                })
        },
        floatingActionButton = {
            onClickFloatingActionButton ?: return@Scaffold
            FloatingActionButton(
                shape = FloatingActionButtonDefaults.shape,
                onClick = onClickFloatingActionButton,
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppScaffoldWithFloatingActionButtonPreview() {
    AppTheme {
        AppScaffold(
            drawerState = rememberDrawerState(DrawerValue.Closed),
            onClickTitleIcon = {},
            onClickFloatingActionButton = {},
        ) {
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppScaffoldWithoutFloatingActionButtonPreview() {
    AppTheme {
        AppScaffold(
            drawerState = rememberDrawerState(DrawerValue.Closed),
            onClickTitleIcon = {},
            onClickFloatingActionButton = null,
        ) {
        }
    }
}