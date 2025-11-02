package com.example.navigationtest.home

import com.example.navigationtest.core.util.LoadingState
import com.example.navigationtest.domain.entity.Tweet

data class HomeUiState(
    val tweets: LoadingState<List<Tweet>> = LoadingState.Loading,
)
