package com.example.navigationtest.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.util.LoadingState
import com.example.navigationtest.domain.entity.Profile
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val route = savedStateHandle.toRoute<ProfileDestination>()
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    suspend fun load() {
        _uiState.update { state -> state.copy(profile = LoadingState.Loading) }

        delay(1000)

        _uiState.update { state ->
            state.copy(
                profile = LoadingState.Success(
                    Profile(
                        id = route.id,
                        name = "name ${route.id}",
                        description = "description ${route.id}",
                    ),
                ),
            )
        }
    }

    init {
        viewModelScope.launch { load() }
    }
}
