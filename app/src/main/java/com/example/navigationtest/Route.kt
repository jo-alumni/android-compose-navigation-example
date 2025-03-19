package com.example.navigationtest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigationtest.page.home.HomeScreen
import com.example.navigationtest.page.home.HomeViewModel
import com.example.navigationtest.page.profile.ProfileScreen
import com.example.navigationtest.page.profile.ProfileViewModel
import com.example.navigationtest.page.tweetDetail.TweetDetailScreen
import com.example.navigationtest.page.tweetDetail.TweetDetailViewModel
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
    data class TweetDetailRoute(
        val id: Int,
    ) : Route()
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.HomeRoute,
    ) {
        composable<Route.HomeRoute> {
            val viewModel = viewModel<HomeViewModel>()
            HomeScreen(
                uiState = viewModel.uiState,
                navigateProfileMine = { navController.navigate(route = Route.ProfileRoute.MineRoute) },
                navigateTweet = { navController.navigate(route = Route.TweetDetailRoute(it.id)) },
            )
        }
        composable<Route.ProfileRoute.MineRoute> {
            val viewModel = viewModel<ProfileViewModel>()
            ProfileScreen(
                uiState = viewModel.uiState,
                navigateBack = { navController.popBackStack() },
            )
        }
        composable<Route.TweetDetailRoute> {
            val viewModel = viewModel<TweetDetailViewModel>()
            val tweet = viewModel.tweet.collectAsState().value
            TweetDetailScreen(
                tweet = tweet,
                navigateBack = { navController.popBackStack() },
            )
        }
    }
}
