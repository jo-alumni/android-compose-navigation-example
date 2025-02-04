package com.example.navigation_test

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigation_test.page.home.HomeScreen
import com.example.navigation_test.page.home.HomeViewModel
import com.example.navigation_test.page.profile.ProfileScreen
import com.example.navigation_test.page.profile.ProfileViewModel
import com.example.navigation_test.page.tweet.TweetScreen
import com.example.navigation_test.page.tweet.TweetViewModel
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

    @Serializable
    data class Tweet(val tweet: com.example.navigation_test.entity.Tweet) : Route()
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Home
    ) {
        composable<Route.Home> {
            val viewModel = viewModel<HomeViewModel>()
            HomeScreen(
                uiState = viewModel.uiState,
                navigateProfileMine = { navController.navigate(route = Route.Profile.Mine) },
            )
        }
        composable<Route.Profile> {
            val viewModel = viewModel<ProfileViewModel>()
            ProfileScreen(
                uiState = viewModel.uiState,
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<Route.Tweet> { backstack ->
            val viewModel = viewModel<TweetViewModel>()
            TweetScreen(
                tweet = viewModel.tweet,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}