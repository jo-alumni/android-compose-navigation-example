package com.example.navigationtest.profile

import com.example.navigationtest.core.util.LoadingState
import com.example.navigationtest.domain.entity.Tweet

data class TweetDetailUiState(
    val tweet: LoadingState<Tweet> = LoadingState.Loading,
)
