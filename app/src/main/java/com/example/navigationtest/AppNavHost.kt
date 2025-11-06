package com.example.navigationtest

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.navigationtest.home.navigation.HomeDestination
import com.example.navigationtest.home.navigation.homeScreen
import com.example.navigationtest.profile.navigation.navigateToProfile
import com.example.navigationtest.profile.navigation.navigateToTweetDetail
import com.example.navigationtest.profile.navigation.profileScreen
import com.example.navigationtest.profile.navigation.tweetDetailScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeDestination,
    ) {
        homeScreen(
            navigateProfile = navController::navigateToProfile,
            navigateTweet = navController::navigateToTweetDetail,
        )
        profileScreen(
            navigateBack = navController::popBackStack,
        )
        tweetDetailScreen(
            navigateBack = navController::popBackStack,
        )
    }
}
