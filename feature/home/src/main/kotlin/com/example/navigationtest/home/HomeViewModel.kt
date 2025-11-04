package com.example.navigationtest.home

import androidx.lifecycle.viewModelScope
import com.example.navigationtest.core.util.StateViewModel
import com.example.navigationtest.domain.entity.Profile
import com.example.navigationtest.domain.entity.Tweet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : StateViewModel<HomeUiState, HomeUiEvent>(initialState = HomeUiState.Default) {
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
