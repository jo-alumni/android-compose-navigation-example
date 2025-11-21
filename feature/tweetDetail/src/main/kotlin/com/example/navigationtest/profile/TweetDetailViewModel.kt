package com.example.navigationtest.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.util.ContractedViewModel
import com.example.navigationtest.domain.usecase.GetTweetUseCase
import com.example.navigationtest.profile.contract.TweetDetailEvent
import com.example.navigationtest.profile.contract.TweetDetailState
import com.example.navigationtest.profile.navigation.TweetDetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TweetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTweetUseCase: GetTweetUseCase,
) : ContractedViewModel<TweetDetailState, TweetDetailEvent>(
    initialState = TweetDetailState.Loading(savedStateHandle.toRoute<TweetDetailDestination>().id),
) {
    fun load() = viewModelScope.launch {
        mutableUiState.update { state -> TweetDetailState.Loading(state.id) }
        runCatching {
            getTweetUseCase.execute(GetTweetUseCase.Args(currentState.id))
        }.fold(
            onSuccess = {
                mutableUiState.update { state ->
                    TweetDetailState.Success(id = state.id, tweet = it)
                }
            },
            onFailure = {
                mutableUiState.update { state ->
                    TweetDetailState.Error(id = state.id, cause = it)
                }
            },
        )
    }

    init {
        load()
    }
}
