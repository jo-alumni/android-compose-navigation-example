package com.example.navigationtest.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.util.ContractedViewModel
import com.example.navigationtest.domain.usecase.GetTweetUseCase
import com.example.navigationtest.profile.contract.TweetDetailUiEvent
import com.example.navigationtest.profile.contract.TweetDetailUiState
import com.example.navigationtest.profile.navigation.TweetDetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TweetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTweetUseCase: GetTweetUseCase,
) : ContractedViewModel<TweetDetailUiState, TweetDetailUiEvent>(
    initialState = TweetDetailUiState.Loading(savedStateHandle.toRoute<TweetDetailDestination>().id),
) {
    fun load() = viewModelScope.launch {
        mutableUiState.update { state -> TweetDetailUiState.Loading(state.id) }
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
        load()
    }
}
