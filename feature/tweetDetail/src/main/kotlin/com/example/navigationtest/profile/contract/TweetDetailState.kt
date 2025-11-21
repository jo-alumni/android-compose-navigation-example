package com.example.navigationtest.profile.contract

import com.example.navigationtest.core.util.State
import com.example.navigationtest.domain.entity.Tweet

internal sealed interface TweetDetailState : State {
    val id: String

    data class Loading(
        override val id: String,
    ) : TweetDetailState

    data class Success(
        override val id: String,
        val tweet: Tweet,
    ) : TweetDetailState

    data class Error(
        override val id: String,
        val cause: Throwable? = null,
    ) : TweetDetailState
}
