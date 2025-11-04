package com.example.navigationtest.profile

import com.example.navigationtest.core.util.State
import com.example.navigationtest.domain.entity.Tweet

sealed interface TweetDetailUiState : State {
    val id: Int

    data class Loading(
        override val id: Int,
    ) : TweetDetailUiState

    data class Success(
        override val id: Int,
        val tweet: Tweet,
    ) : TweetDetailUiState

    data class Error(
        override val id: Int,
    ) : TweetDetailUiState

    companion object {
        fun default(tweetId: Int): TweetDetailUiState = Loading(tweetId)
    }
}
