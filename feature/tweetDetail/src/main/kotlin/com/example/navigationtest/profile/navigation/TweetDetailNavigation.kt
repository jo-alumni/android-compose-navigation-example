package com.example.navigationtest.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.navigationtest.profile.TweetDetailRoot
import kotlinx.serialization.Serializable

@Serializable
internal data class TweetDetailDestination(val id: Int)

fun NavGraphBuilder.tweetDetailScreen(
    navigateBack: () -> Unit,
) {
    composable<TweetDetailDestination> {
        TweetDetailRoot(navigateBack = navigateBack)
    }
}

fun NavController.navigateToTweetDetail(id: Int, navOptions: NavOptions? = null) = navigate(TweetDetailDestination(id), navOptions)
