package com.example.navigationtest.profile

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class ProfileDestination(val id: String)

fun NavGraphBuilder.profileScreen(
    navigateBack: () -> Unit,
) {
    composable<ProfileDestination> {
        val viewModel = viewModel<ProfileViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ProfileScreen(
            uiState = uiState,
            navigateBack = navigateBack,
        )
    }
}

fun NavController.navigateToProfile(id: String, navOptions: NavOptions? = null) = navigate(ProfileDestination(id), navOptions)
