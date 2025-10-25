package com.example.navigationtest.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.ui.entity.Tweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TweetDetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val route = savedStateHandle.toRoute<TweetDetail>()
    private val _tweet = MutableStateFlow<Tweet?>(null)

    val tweet = _tweet.asStateFlow()

    init {
        viewModelScope.launch {
            _tweet.value =
                Tweet(
                    id = route.id,
                    name = "name${route.id}",
                    content = "content${route.id}",
                )
        }
    }
}
