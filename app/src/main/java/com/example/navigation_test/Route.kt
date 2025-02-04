package com.example.navigation_test

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigation_test.page.home.HomeViewModel
import com.example.navigation_test.page.home.TweetScreen
import com.example.navigation_test.page.profile.ProfileScreen
import com.example.navigation_test.page.profile.ProfileViewModel
import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Home : Route()

    @Serializable
    sealed class Profile : Route() {

        @Serializable
        data object Mine : Route()

        @Serializable
        data object Other : Route()
    }
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Home
    ) {
        composable<Route.Home> {
            val viewModel: HomeViewModel = viewModel()
            TweetScreen(
                uiState = viewModel.uiState,
                navigateSecondScreen = { navController.navigate(route = Route.Profile.Mine) },
            )
        }
        composable<Route.Profile> {
            val viewModel: ProfileViewModel = viewModel()
            ProfileScreen(
                uiState = viewModel.uiState,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}