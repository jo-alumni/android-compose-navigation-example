package com.example.navigationtest.profile

import com.example.navigationtest.core.util.LoadingState
import com.example.navigationtest.domain.entity.Profile

data class ProfileUiState(
    val profile: LoadingState<Profile> = LoadingState.Loading,
)
