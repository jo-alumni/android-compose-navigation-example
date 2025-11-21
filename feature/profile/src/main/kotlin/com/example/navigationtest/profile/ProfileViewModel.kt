package com.example.navigationtest.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.util.ContractedViewModel
import com.example.navigationtest.domain.usecase.GetProfileUseCase
import com.example.navigationtest.profile.contract.ProfileEvent
import com.example.navigationtest.profile.contract.ProfileState
import com.example.navigationtest.profile.navigation.ProfileDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProfileUseCase: GetProfileUseCase,
) : ContractedViewModel<ProfileState, ProfileEvent>(
    initialState = ProfileState.Loading(savedStateHandle.toRoute<ProfileDestination>().id),
) {
    fun load() = viewModelScope.launch {
        mutableUiState.update { state -> ProfileState.Loading(id = state.id) }
        runCatching {
            getProfileUseCase.execute(GetProfileUseCase.Args(id = currentState.id))
        }.fold(
            onSuccess = {
                mutableUiState.update { state ->
                    ProfileState.Success(id = state.id, profile = it)
                }
            },
            onFailure = {
                mutableUiState.update { state ->
                    ProfileState.Error(id = state.id, cause = it)
                }
            },
        )
    }

    init {
        load()
    }
}
