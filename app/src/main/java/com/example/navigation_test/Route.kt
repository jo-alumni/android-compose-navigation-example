package com.example.navigation_test

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.navigation_test.feature.first.registerFirstNavigation
import com.example.navigation_test.feature.second.registerSecondNavigation

sealed class Route {
    data object First : Route()
    data object Second : Route()
}

@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Route.First
    ) {
        registerFirstNavigation(
            navController = navController,
            paddingValues = innerPadding
        )
        registerSecondNavigation(
            navController = navController,
            paddingValues = innerPadding
        )
    }
}