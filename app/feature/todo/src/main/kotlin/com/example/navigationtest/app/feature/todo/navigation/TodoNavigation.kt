package com.example.navigationtest.app.feature.todo.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.navigationtest.app.feature.todo.TodoRoot
import kotlinx.serialization.Serializable

@Serializable
data object TodoDestination

fun NavGraphBuilder.todoScreen(
    navigatePostDetail: (Int) -> Unit,
) {
    composable<TodoDestination> {
        TodoRoot(
            modifier = Modifier.fillMaxSize(),
            drawerState = rememberDrawerState(DrawerValue.Closed),
            navigatePostDetail = navigatePostDetail,
        )
    }
}

fun NavController.navigateTodo(navOptions: NavOptions? = null) = navigate(route = TodoDestination, navOptions = navOptions)


