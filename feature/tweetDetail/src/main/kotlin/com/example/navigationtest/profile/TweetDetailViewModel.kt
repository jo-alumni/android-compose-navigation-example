package com.example.navigationtest.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.util.StateViewModel
import com.example.navigationtest.domain.usecase.GetTweetUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class TweetDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val getTweetUseCase: GetTweetUseCase,
) : StateViewModel<TweetDetailUiState, TweetDetailUiEvent>(
    initialState = TweetDetailUiState.Loading(savedStateHandle.toRoute<TweetDetailDestination>().id),
) {
    suspend fun load() {
        mutableUiState.update { state -> TweetDetailUiState.Loading(state.id) }
        delay(1000)
        runCatching {
            getTweetUseCase.execute(GetTweetUseCase.Args(currentState.id))
        }.fold(
            onSuccess = {
                mutableUiState.update { state ->
                    TweetDetailUiState.Success(id = state.id, tweet = it)
                }
            },
            onFailure = {
                mutableUiState.update { state ->
                    TweetDetailUiState.Error(id = state.id, cause = it)
                }
            },
        )
    }

    init {
        viewModelScope.launch { load() }
    }
}
