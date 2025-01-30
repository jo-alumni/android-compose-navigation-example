package com.example.navigation_test.page.second

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation_test.Route

fun NavGraphBuilder.registerSecondNavigation(
    navController: NavController,
    paddingValues: PaddingValues
) {
    composable<Route.Second> { backstack ->
        val viewModel: SecondViewModel = viewModel(backstack)
        SecondScreen(
            modifier = Modifier.padding(paddingValues),
            uiState = viewModel.uiState,
            navigateFirstScreen = {
                navController.navigate(route = Route.First) {
                    popUpTo<Route.First> {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}