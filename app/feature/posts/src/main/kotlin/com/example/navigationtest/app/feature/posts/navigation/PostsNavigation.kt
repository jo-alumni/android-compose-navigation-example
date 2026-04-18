package com.example.navigationtest.app.feature.posts.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.navigationtest.app.feature.posts.PostsRoot
import kotlinx.serialization.Serializable

@Serializable
data object PostsDestination

fun NavGraphBuilder.homeScreen(
    navigatePostDetail: (Int) -> Unit,
    navigateTodo: () -> Unit,
) {
    composable<PostsDestination> {
        PostsRoot(
            modifier = Modifier.fillMaxSize(),
            navigatePostDetail = navigatePostDetail,
            navigateTodo = navigateTodo,
        )
    }
}

fun NavController.navigatePosts(navOptions: NavOptions? = null) = navigate(route = PostsDestination, navOptions = navOptions)


