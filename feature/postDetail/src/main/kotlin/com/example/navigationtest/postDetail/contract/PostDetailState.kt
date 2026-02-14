package com.example.navigationtest.postDetail.contract

import com.example.navigationtest.core.util.State
import com.example.navigationtest.domain.entity.Post

internal sealed interface PostDetailState : State {
    val id: Int

    data class Loading(
        override val id: Int,
    ) : PostDetailState

    data class Success(
        override val id: Int,
        val post: Post,
    ) : PostDetailState

    data class Error(
        override val id: Int,
        val cause: Throwable? = null,
    ) : PostDetailState
}
