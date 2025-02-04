package com.example.navigation_test.page.tweet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.navigation_test.Route

class TweetViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val tweet = savedStateHandle.toRoute<Route.Tweet>().tweet
}