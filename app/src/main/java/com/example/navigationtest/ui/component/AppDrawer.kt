package com.example.navigationtest.ui.component

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
import androidx.compose.ui.unit.dp
import com.example.navigationtest.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun AppNavigationDrawer(
    modifier: Modifier = Modifier,
    navigateProfile: () -> Unit,
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
                    modifier =
                        Modifier
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Drawer Title",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge,
                    )

                    HorizontalDivider()

                    Text(
                        "Section 1",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    NavigationDrawerItem(
                        label = { Text("Profile") },
                        selected = false,
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                        onClick = {
                            navigateProfile()
                            scope.launch { drawerState.close() }
                        },
                    )
                }
            }
        },
    ) {
        content()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AppNavigationDrawerPreview() {
    AppTheme {
        AppNavigationDrawer(
            navigateProfile = {},
            drawerState = rememberDrawerState(DrawerValue.Open),
        ) {
        }
    }
}
