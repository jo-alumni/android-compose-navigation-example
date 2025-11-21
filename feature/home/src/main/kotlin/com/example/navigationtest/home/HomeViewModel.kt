package com.example.navigationtest.home

import androidx.lifecycle.viewModelScope
import com.example.navigationtest.core.util.ContractedViewModel
import com.example.navigationtest.domain.usecase.GetTweetListUseCase
import com.example.navigationtest.domain.usecase.execute
import com.example.navigationtest.home.contract.HomeEvent
import com.example.navigationtest.home.contract.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getTweetListUseCase: GetTweetListUseCase,
) : ContractedViewModel<HomeState, HomeEvent>(
    initialState = HomeState.Loading(emptyList()),
) {
    fun load() = viewModelScope.launch {
        mutableUiState.update { state -> HomeState.Loading(tweets = state.tweets) }
        runCatching {
            getTweetListUseCase.execute()
        }.fold(
            onSuccess = {
                mutableUiState.update { _ -> HomeState.Success(tweets = it) }
                mutableUiEvent.emit(HomeEvent.ShowSnackbar("Success"))
            },
            onFailure = {
                mutableUiState.update { state -> HomeState.Error(tweets = state.tweets, cause = it) }
            },
        )
    }

    init {
        load()
    }
}
