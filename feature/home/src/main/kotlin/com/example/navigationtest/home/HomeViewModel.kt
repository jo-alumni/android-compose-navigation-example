package com.example.navigationtest.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationtest.core.entity.Tweet
import com.example.navigationtest.core.util.LoadingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchTweets()
        }
    }

    private suspend fun fetchTweets() {
        _uiState.update { it.copy(tweets = LoadingState.Loading) }
        delay(10000)
        _uiState.update {
            it.copy(
                LoadingState.Success(
                    (1..50).map { Tweet(id = it, name = "name$it", content = "content$it") },
                ),
            )
        }
    }
}
