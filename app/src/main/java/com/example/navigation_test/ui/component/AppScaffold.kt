package com.example.navigation_test.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    drawerState: DrawerState,
    onClickTitleIcon: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    val scope = rememberCoroutineScope()
    Scaffold(
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
    ) { paddingValues ->
        content(paddingValues)
    }
}