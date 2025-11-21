package com.example.navigationtest.profile.contract

import com.example.navigationtest.core.util.State
import com.example.navigationtest.domain.entity.Profile

internal sealed interface ProfileState : State {
    val id: String

    data class Loading(
        override val id: String,
    ) : ProfileState

    data class Success(
        override val id: String,
        val profile: Profile,
    ) : ProfileState

    data class Error(
        override val id: String,
        val cause: Throwable? = null,
    ) : ProfileState
}
