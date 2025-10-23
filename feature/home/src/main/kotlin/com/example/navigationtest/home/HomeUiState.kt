package com.example.navigationtest.home

import com.example.navigationtest.core.ui.entity.Tweet

data class HomeUiState(
    val tweets: List<Tweet> = emptyList(),
)
