package com.example.navigation_test.page.first

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navigation_test.page.first.state.FirstUiState

@Composable
internal fun FirstScreen(
    modifier: Modifier = Modifier,
    uiState: FirstUiState,
    navigateSecondScreen: () -> Unit,
) {
    Column(modifier = modifier) {
        Text(text = uiState.text)
        Button(onClick = navigateSecondScreen) {
            Text("navigate second screen")
        }
    }
}