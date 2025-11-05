package com.example.navigationtest.profile.contract

import com.example.navigationtest.core.util.State
import com.example.navigationtest.domain.entity.Profile

internal sealed interface ProfileUiState : State {
    val id: String

    data class Loading(
        override val id: String,
    ) : ProfileUiState

    data class Success(
        override val id: String,
        val profile: Profile,
    ) : ProfileUiState

    data class Error(
        override val id: String,
        val cause: Throwable? = null,
    ) : ProfileUiState
}
