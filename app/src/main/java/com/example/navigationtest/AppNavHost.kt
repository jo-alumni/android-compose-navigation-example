package com.example.navigationtest

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.navigationtest.home.Home
import com.example.navigationtest.home.homeScreen
import com.example.navigationtest.profile.navigateToProfile
import com.example.navigationtest.profile.navigateToTweetDetail
import com.example.navigationtest.profile.profileScreen
import com.example.navigationtest.profile.tweetDetailScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Home,
    ) {
        homeScreen(
            navigateProfileMine = { navController.navigateToProfile() },
            navigateTweet = { tweet -> navController.navigateToTweetDetail(tweet.id) },
        )
        profileScreen(
            navigateBack = { navController.popBackStack() },
        )
        tweetDetailScreen(
            navigateBack = { navController.popBackStack() },
        )
    }
}
