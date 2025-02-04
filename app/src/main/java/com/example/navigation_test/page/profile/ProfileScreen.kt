package com.example.navigation_test.page.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    navigateFirstScreen: () -> Unit,
) {
    Column(modifier = modifier) {
        Text(text = uiState.text)
        Button(onClick = navigateFirstScreen) {
            Text("navigate first screen")
        }
    }
}