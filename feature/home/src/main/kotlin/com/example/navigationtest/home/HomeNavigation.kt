package com.example.navigationtest.home

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.navigationtest.core.ui.entity.Tweet
import kotlinx.serialization.Serializable

@Serializable data object Home

fun NavGraphBuilder.homeScreen(
    navigateProfileMine: () -> Unit = {},
    navigateTweet: (Tweet) -> Unit = {},
) {
    composable<Home> {
        val viewModel = viewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        HomeScreen(
            uiState = uiState,
            navigateProfileMine = navigateProfileMine,
            navigateTweet = navigateTweet,
        )
    }
}

fun NavController.navigateHome(navOptions: NavOptions) = navigate(route = Home, navOptions = navOptions)


