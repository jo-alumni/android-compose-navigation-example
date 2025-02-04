package com.example.navigation_test.page.home

import com.example.navigation_test.entity.Tweet

data class HomeUiState(
    val tweets: List<Tweet> = emptyList(),
)
