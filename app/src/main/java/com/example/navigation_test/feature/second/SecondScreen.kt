package com.example.navigation_test.feature.second

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navigation_test.feature.second.state.SecondUiState


@Composable
internal fun SecondScreen(
    modifier: Modifier = Modifier,
    uiState: SecondUiState,
    navigateFirstScreen: () -> Unit,
) {
    Column(modifier = modifier) {
        Text(text = uiState.text)
        Button(onClick = navigateFirstScreen) {
            Text("navigate first screen")
        }
    }
}