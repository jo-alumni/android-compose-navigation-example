package com.example.navigationtest.app.feature.postDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.navigationtest.app.feature.postDetail.TweetDetailRoot
import kotlinx.serialization.Serializable

@Serializable
internal data class PostDetailDetailDestination(val id: Int)

fun NavGraphBuilder.tweetDetailScreen(
    navigateBack: () -> Unit,
) {
    composable<PostDetailDetailDestination> {
        TweetDetailRoot(navigateBack = navigateBack)
    }
}

fun NavController.navigateToPostDetail(id: Int, navOptions: NavOptions? = null) = navigate(PostDetailDetailDestination(id), navOptions)
