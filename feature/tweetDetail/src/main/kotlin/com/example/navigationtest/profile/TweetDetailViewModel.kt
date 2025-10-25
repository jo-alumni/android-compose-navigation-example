package com.example.navigationtest.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.entity.Profile
import com.example.navigationtest.core.entity.Tweet
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
                    content = "content${route.id}",
                    postUser = Profile(
                        id = route.id,
                        name = "user_name_${route.id}",
                        description = "description_${route.id}",
                    ),
                )
        }
    }
}
