package com.example.navigation_test.page.first

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation_test.Route

fun NavGraphBuilder.registerFirstNavigation(
    navController: NavController,
    paddingValues: PaddingValues,
) {
    composable<Route.First> { backstack ->
        val viewModel: FirstViewModel = viewModel(backstack)
        FirstScreen(
            modifier = Modifier.padding(paddingValues),
            uiState = viewModel.uiState,
            navigateSecondScreen = {
                navController.navigate(route = Route.Second) {
                    popUpTo<Route.Second> {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}