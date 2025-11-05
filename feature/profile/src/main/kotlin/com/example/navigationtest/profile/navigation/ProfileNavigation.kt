package com.example.navigationtest.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.navigationtest.profile.ProfileRoot
import kotlinx.serialization.Serializable

@Serializable
internal data class ProfileDestination(val id: String)

fun NavGraphBuilder.profileScreen(
    navigateBack: () -> Unit,
) {
    composable<ProfileDestination> {
        ProfileRoot(navigateBack = navigateBack)
    }
}

fun NavController.navigateToProfile(id: String, navOptions: NavOptions? = null) = navigate(ProfileDestination(id), navOptions)
