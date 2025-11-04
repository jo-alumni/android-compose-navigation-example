package com.example.navigationtest.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.util.StateViewModel
import com.example.navigationtest.domain.entity.Profile
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    savedStateHandle: SavedStateHandle,
) : StateViewModel<ProfileUiState, ProfileUiEvent>(
    initialState = ProfileUiState.default(savedStateHandle.toRoute<ProfileDestination>().id),
) {
    suspend fun load() {
        _uiState.update { state -> ProfileUiState.Loading(id = state.id) }
        delay(1000)
        _uiState.update { state ->
            ProfileUiState.Success(
                id = state.id,
                profile = Profile(
                    id = state.id,
                    name = "name ${state.id}",
                    description = "description ${state.id}",
                ),
            )
        }
    }

    init {
        viewModelScope.launch { load() }
    }
}
