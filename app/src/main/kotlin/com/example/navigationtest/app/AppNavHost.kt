package com.example.navigationtest.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.navigationtest.app.feature.postDetail.navigation.navigateToPostDetail
import com.example.navigationtest.app.feature.postDetail.navigation.tweetDetailScreen
import com.example.navigationtest.app.feature.posts.navigation.PostsDestination
import com.example.navigationtest.app.feature.posts.navigation.homeScreen
import com.example.navigationtest.app.feature.todo.navigation.navigateTodo
import com.example.navigationtest.app.feature.todo.navigation.todoScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = PostsDestination,
    ) {
        homeScreen(
            navigatePostDetail = navController::navigateToPostDetail,
            navigateTodo = navController::navigateTodo,
        )
        tweetDetailScreen(
            navigateBack = navController::popBackStack,
        )
        todoScreen(
            navigateBack = navController::popBackStack,
        )
    }
}
