package com.example.navigationtest.profile

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class TweetDetail(val id: Int)

fun NavGraphBuilder.tweetDetailScreen(
    navigateBack: () -> Unit,
) {
    composable<TweetDetail> {
        val viewModel = viewModel<TweetDetailViewModel>()
        val tweet by viewModel.tweet.collectAsStateWithLifecycle()
        TweetDetailScreen(
            tweet = tweet,
            navigateBack = navigateBack,
        )
    }
}
