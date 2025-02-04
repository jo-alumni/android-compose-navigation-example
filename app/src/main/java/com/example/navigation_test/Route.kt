package com.example.navigation_test

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.navigation_test.page.profile.ProfileScreen
import com.example.navigation_test.page.profile.ProfileViewModel
import com.example.navigation_test.page.tweet.TweetScreen
import com.example.navigation_test.page.tweet.TweetViewModel
import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Tweet : Route()

    @Serializable
    data object Profile : Route()

    @Serializable
    data object Third : Route()

    @Serializable
    data object Fourth : Route()
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Tweet
    ) {
        composable<Route.Tweet> { backstack ->
            val viewModel: TweetViewModel = viewModel()
            TweetScreen(
                route = backstack.toRoute<Route.Tweet>(),
                uiState = viewModel.uiState,
                navigateSecondScreen = {
                    navController.navigate(route = Route.Profile) {
                        popUpTo(Route.Profile)
                        launchSingleTop = true
                    }
                },
                navigateThirdScreen = {},
                navigateFourthScreen = {}
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