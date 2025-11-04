package com.example.navigationtest.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.util.StateViewModel
import com.example.navigationtest.domain.entity.Profile
import com.example.navigationtest.domain.entity.Tweet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TweetDetailViewModel(
    savedStateHandle: SavedStateHandle,
) : StateViewModel<TweetDetailUiState, TweetDetailUiEvent>(
    initialState = TweetDetailUiState.default(savedStateHandle.toRoute<TweetDetailDestination>().id),
) {
    suspend fun load() {
        mutableUiState.update { state -> TweetDetailUiState.Loading(state.id) }
        delay(1000)
        mutableUiState.update { state ->
            TweetDetailUiState.Success(
                id = state.id,
                tweet =
                    Tweet(
                        id = state.id,
                        content = "content${state.id}",
                        postUser = Profile(
                            id = "user_${state.id}",
                            name = "user_name_${state.id}",
                            description = "description_${state.id}",
                        ),
                    ),
            )
        }
    }

    init {
        viewModelScope.launch { load() }
    }
}
