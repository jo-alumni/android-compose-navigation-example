package com.example.navigation_test.page.tweet

import com.example.navigation_test.entity.Tweet

data class TweetUiState(
    val tweets: List<Tweet> = emptyList(),
)
