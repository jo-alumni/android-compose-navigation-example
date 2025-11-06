package com.example.navigationtest.profile.contract

import com.example.navigationtest.core.util.State
import com.example.navigationtest.domain.entity.Tweet

internal sealed interface TweetDetailUiState : State {
    val id: String

    data class Loading(
        override val id: String,
    ) : TweetDetailUiState

    data class Success(
        override val id: String,
        val tweet: Tweet,
    ) : TweetDetailUiState

    data class Error(
        override val id: String,
        val cause: Throwable? = null,
    ) : TweetDetailUiState
}
