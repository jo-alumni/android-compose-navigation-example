package com.example.navigationtest.profile

import com.example.navigationtest.core.util.LoadingState
import com.example.navigationtest.domain.entity.Profile

data class ProfileUiState(
    val profile: LoadingState<Profile>,
) {
    companion object {
        fun loading() = ProfileUiState(
            profile = LoadingState.Loading,
        )
    }
}
