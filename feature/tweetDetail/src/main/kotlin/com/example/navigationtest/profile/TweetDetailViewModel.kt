package com.example.navigationtest.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.util.LoadingState
import com.example.navigationtest.domain.entity.Profile
import com.example.navigationtest.domain.entity.Tweet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TweetDetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val route = savedStateHandle.toRoute<TweetDetailDestination>()
    private val _uiState = MutableStateFlow(TweetDetailUiState())
    val uiState = _uiState.asStateFlow()

    suspend fun loadTweet() {
        _uiState.update { state -> state.copy(tweet = LoadingState.Loading) }
        delay(1000)
        _uiState.update { state ->
            state.copy(
                tweet = LoadingState.Success(
                    Tweet(
                        id = route.id,
                        content = "content${route.id}",
                        postUser = Profile(
                            id = "user_${route.id}",
                            name = "user_name_${route.id}",
                            description = "description_${route.id}",
                        ),
                    ),
                ),
            )
        }
    }

    init {
        viewModelScope.launch { loadTweet() }
    }
}
