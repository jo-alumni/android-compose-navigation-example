package com.example.navigation_test

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigation_test.page.first.FirstScreen
import com.example.navigation_test.page.first.FirstViewModel
import com.example.navigation_test.page.second.SecondScreen
import com.example.navigation_test.page.second.SecondViewModel
import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object First : Route()

    @Serializable
    data object Second : Route()
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.First
    ) {
        composable<Route.First> { backstack ->
            val viewModel: FirstViewModel = viewModel(backstack)
            FirstScreen(
                uiState = viewModel.uiState,
                navigateSecondScreen = {
                    navController.navigate(route = Route.Second) {
                        popUpTo<Route.Second> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Route.Second> { backstack ->
            val viewModel: SecondViewModel = viewModel(backstack)
            SecondScreen(
                uiState = viewModel.uiState,
                navigateFirstScreen = {
                    navController.navigate(route = Route.First) {
                        popUpTo<Route.First> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}