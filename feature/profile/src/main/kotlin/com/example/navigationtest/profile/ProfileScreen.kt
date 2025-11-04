package com.example.navigationtest.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.navigationtest.core.ui.theme.AppTheme
import com.example.navigationtest.core.util.render
import com.example.navigationtest.domain.entity.Profile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    navigateBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack,
                    ) {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
                    }
                },
            )
        },
    ) { paddingValues ->
        uiState.render<ProfileUiState.Error> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Failure",
                )
            }
        }
        uiState.render<ProfileUiState.Loading> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }


        uiState.render<ProfileUiState.Success> {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                Text("$profile")
            }
        }

    }
}

private class UiStateParameterProvider : PreviewParameterProvider<ProfileUiState> {
    override val values: Sequence<ProfileUiState>
        get() = sequenceOf(
            ProfileUiState.Loading(id = "1"),
            ProfileUiState.Success(id = "1", profile = Profile.fake()),
            ProfileUiState.Error(id = "1"),
        )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenPreview(
    @PreviewParameter(UiStateParameterProvider::class) uiState: ProfileUiState,
) {
    AppTheme {
        ProfileScreen(
            uiState = uiState,
            navigateBack = {},
        )
    }
}
