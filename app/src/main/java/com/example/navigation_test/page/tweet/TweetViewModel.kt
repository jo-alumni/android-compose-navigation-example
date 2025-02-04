package com.example.navigation_test.page.tweet

import androidx.lifecycle.ViewModel
import com.example.navigation_test.entity.Tweet

class TweetViewModel : ViewModel() {
    val uiState = TweetUiState(
        tweets = (1..50).map {
            Tweet(
                id = it,
                name = "name$it",
                content = "content$it",
            )
        }
    )
}
