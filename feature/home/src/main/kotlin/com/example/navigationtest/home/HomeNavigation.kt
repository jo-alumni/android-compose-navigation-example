package com.example.navigationtest.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.navigationtest.core.entity.Profile
import com.example.navigationtest.core.entity.Tweet
import kotlinx.serialization.Serializable

@Serializable
data object Home

fun NavGraphBuilder.homeScreen(
    navigateProfile: (Profile) -> Unit,
    navigateTweet: (Tweet) -> Unit,
) {
    composable<Home> {
        val viewModel = viewModel<HomeViewModel>()
        HomeRoot(
            modifier = Modifier.fillMaxSize(),
            viewModel = viewModel,
            drawerState = rememberDrawerState(DrawerValue.Closed),
            navigateProfile = navigateProfile,
            navigateTweet = navigateTweet,
        )
    }
}

fun NavController.navigateHome(navOptions: NavOptions? = null) = navigate(route = Home, navOptions = navOptions)


