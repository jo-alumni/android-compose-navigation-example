package com.example.navigationtest.page.tweetDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.Route
import com.example.navigationtest.entity.Tweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TweetDetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val tweetId = savedStateHandle.toRoute<Route.TweetDetailRoute>().id
    private val _tweet = MutableStateFlow<Tweet?>(null)

    val tweet = _tweet.asStateFlow()

    init {
        viewModelScope.launch {
            _tweet.value =
                Tweet(
                    id = tweetId,
                    name = "name$tweetId",
                    content = "content$tweetId",
                )
        }
    }
}
