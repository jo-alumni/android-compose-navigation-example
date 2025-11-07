package com.example.navigationtest.home

import androidx.lifecycle.viewModelScope
import com.example.navigationtest.core.util.StateViewModel
import com.example.navigationtest.domain.usecase.GetTweetListUseCase
import com.example.navigationtest.domain.usecase.execute
import com.example.navigationtest.home.contract.HomeUiEvent
import com.example.navigationtest.home.contract.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getTweetListUseCase: GetTweetListUseCase,
) : StateViewModel<HomeUiState, HomeUiEvent>(
    initialState = HomeUiState.Loading(emptyList()),
) {
    fun load() {
        viewModelScope.launch {
            mutableUiState.update { state -> HomeUiState.Loading(tweets = state.tweets) }
            runCatching {
                getTweetListUseCase.execute()
            }.fold(
                onSuccess = {
                    mutableUiState.update { _ -> HomeUiState.Success(tweets = it) }
                    mutableUiEvent.emit(HomeUiEvent.ShowSnackbar("Success"))
                },
                onFailure = {
                    mutableUiState.update { state -> HomeUiState.Error(tweets = state.tweets, cause = it) }
                },
            )
        }
    }

    init {
        viewModelScope.launch { load() }
    }
}
