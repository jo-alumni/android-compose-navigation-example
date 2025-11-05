package com.example.navigationtest.home.contract

import com.example.navigationtest.core.util.State
import com.example.navigationtest.domain.entity.Tweet

internal sealed interface HomeUiState : State {
    val tweets: List<Tweet>

    data class Loading(
        override val tweets: List<Tweet>,
    ) : HomeUiState

    data class Success(
        override val tweets: List<Tweet>,
    ) : HomeUiState

    data class Error(
        override val tweets: List<Tweet>,
        val cause: Throwable? = null,
    ) : HomeUiState
}
