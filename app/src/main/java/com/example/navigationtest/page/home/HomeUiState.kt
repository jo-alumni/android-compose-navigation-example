package com.example.navigationtest.page.home

import com.example.navigationtest.entity.Tweet

data class HomeUiState(
    val tweets: List<Tweet> = emptyList(),
)
