package com.example.navigationtest.home

import com.example.navigationtest.domain.entity.Tweet

sealed interface HomeUiState {
    val tweets: List<Tweet>

    data class Loading(
        override val tweets: List<Tweet>,
    ) : HomeUiState

    data class Success(
        override val tweets: List<Tweet>,
    ) : HomeUiState

    data class Error(
        override val tweets: List<Tweet>,
        val message: String,
    ) : HomeUiState

    companion object {
        val Default: HomeUiState = Loading(tweets = emptyList())
    }
}
