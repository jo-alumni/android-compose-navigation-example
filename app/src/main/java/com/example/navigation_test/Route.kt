package com.example.navigation_test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
    data object HomeRoute : Route()

    @Serializable
    sealed class ProfileRoute : Route() {

        @Serializable
        data object MineRoute : Route()

        @Serializable
        data object OtherRoute : Route()
    }

    @Serializable
    data class TweetRoute(val id: Int) : Route()
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.HomeRoute
    ) {
        composable<Route.HomeRoute> {
            val viewModel = viewModel<HomeViewModel>()
            HomeScreen(
                uiState = viewModel.uiState,
                navigateProfileMine = { navController.navigate(route = Route.ProfileRoute.MineRoute) },
                navigateTweet = { navController.navigate(route = Route.TweetRoute(it.id)) }
            )
        }
        composable<Route.ProfileRoute.MineRoute> {
            val viewModel = viewModel<ProfileViewModel>()
            ProfileScreen(
                uiState = viewModel.uiState,
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<Route.TweetRoute> { backstack ->
            val viewModel = viewModel<TweetViewModel>()
            val tweet = viewModel.tweet.collectAsState().value
            TweetScreen(
                tweet = tweet,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}