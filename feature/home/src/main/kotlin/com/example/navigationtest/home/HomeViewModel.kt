package com.example.navigationtest.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationtest.domain.entity.Profile
import com.example.navigationtest.domain.entity.Tweet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState.Default)
    val uiState = _uiState.asStateFlow()
    fun load() {
        viewModelScope.launch {
            _uiState.update { state -> HomeUiState.Loading(tweets = state.tweets) }
            delay(1000)
            _uiState.update { _ ->
                HomeUiState.Success(
                    tweets = (1..50).map {
                        Tweet(
                            id = it,
                            content = "content$it",
                            postUser = Profile(
                                id = "user_id_$it",
                                name = "user_name_$it",
                                description = "description_$it",
                            ),
                        )
                    },
                )
            }
        }
    }

    init {
        viewModelScope.launch { load() }
    }
}
