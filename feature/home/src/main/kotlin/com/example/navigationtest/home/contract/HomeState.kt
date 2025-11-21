package com.example.navigationtest.home.contract

import com.example.navigationtest.core.util.State
import com.example.navigationtest.domain.entity.Tweet

internal sealed interface HomeState : State {
    val tweets: List<Tweet>

    data class Loading(
        override val tweets: List<Tweet>,
    ) : HomeState

    data class Success(
        override val tweets: List<Tweet>,
    ) : HomeState

    data class Error(
        override val tweets: List<Tweet>,
        val cause: Throwable? = null,
    ) : HomeState
}
