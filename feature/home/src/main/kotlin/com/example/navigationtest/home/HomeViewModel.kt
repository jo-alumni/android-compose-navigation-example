package com.example.navigationtest.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationtest.core.util.LoadingState
import com.example.navigationtest.domain.entity.Profile
import com.example.navigationtest.domain.entity.Tweet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    suspend fun load() {
        _uiState.update { state -> state.copy(tweets = LoadingState.Loading) }
        delay(1000)
        _uiState.update { state ->
            state.copy(
                LoadingState.Success(
                    (1..50).map {
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
                ),
            )
        }
    }

    init {
        viewModelScope.launch { load() }
    }
}
