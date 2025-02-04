package com.example.navigation_test

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.navigation_test.page.second.SecondScreen
import com.example.navigation_test.page.second.SecondViewModel
import com.example.navigation_test.page.tweet.TweetScreen
import com.example.navigation_test.page.tweet.TweetViewModel
import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Tweet : Route()

    @Serializable
    data object Second : Route()

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
            val viewModel: TweetViewModel = viewModel(backstack)
            TweetScreen(route = backstack.toRoute<Route>(),
                uiState = viewModel.uiState,
                navigateSecondScreen = {
                    navController.navigate(route = Route.Second) {
                        popUpTo<Route.Second> { inclusive = true }
                        launchSingleTop = true
                    }
                },
                navigateThirdScreen = {},
                navigateFourthScreen = {}
            )
        }
        composable<Route.Second> { backstack ->
            val viewModel: SecondViewModel = viewModel(backstack)
            SecondScreen(
                uiState = viewModel.uiState,
                navigateFirstScreen = {
                    navController.navigate(route = Route.Tweet) {
                        popUpTo<Route.Tweet> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}