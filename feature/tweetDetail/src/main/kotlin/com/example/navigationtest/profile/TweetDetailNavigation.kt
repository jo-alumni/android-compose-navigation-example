package com.example.navigationtest.profile

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class TweetDetailDestination(val id: Int)

fun NavGraphBuilder.tweetDetailScreen(
    navigateBack: () -> Unit,
) {
    composable<TweetDetailDestination> {
        val viewModel = viewModel<TweetDetailViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        TweetDetailScreen(
            uiState = uiState,
            navigateBack = navigateBack,
        )
    }
}

fun NavController.navigateToTweetDetail(id: Int, navOptions: NavOptions? = null) = navigate(TweetDetailDestination(id), navOptions)
