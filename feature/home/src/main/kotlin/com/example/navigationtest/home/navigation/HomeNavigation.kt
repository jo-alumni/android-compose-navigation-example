package com.example.navigationtest.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.navigationtest.home.HomeRoot
import kotlinx.serialization.Serializable

@Serializable
data object HomeDestination

fun NavGraphBuilder.homeScreen(
    navigateProfile: (String) -> Unit,
    navigateTweet: (String) -> Unit,
) {
    composable<HomeDestination> {
        HomeRoot(
            modifier = Modifier.fillMaxSize(),
            drawerState = rememberDrawerState(DrawerValue.Closed),
            navigateProfile = navigateProfile,
            navigateTweet = navigateTweet,
        )
    }
}

fun NavController.navigateHome(navOptions: NavOptions? = null) = navigate(route = HomeDestination, navOptions = navOptions)


