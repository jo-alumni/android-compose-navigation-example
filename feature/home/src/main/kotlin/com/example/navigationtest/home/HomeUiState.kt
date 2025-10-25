package com.example.navigationtest.home

import com.example.navigationtest.core.entity.Tweet
import com.example.navigationtest.core.util.LoadingState

data class HomeUiState(
    val tweets: LoadingState<List<Tweet>> = LoadingState.Loading,
)
