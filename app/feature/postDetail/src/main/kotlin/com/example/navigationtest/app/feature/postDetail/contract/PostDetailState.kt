package com.example.navigationtest.app.feature.postDetail.contract

import com.example.navigationtest.app.core.util.State
import com.example.navigationtest.domain.entity.Comment
import com.example.navigationtest.domain.entity.Post

internal sealed interface PostDetailState : State {
    val id: Int

    data class Initial(
        override val id: Int,
    ) : PostDetailState

    data class Loading(
        override val id: Int,
    ) : PostDetailState

    sealed interface Stable : PostDetailState {
        val page: Int
        val canLoadMore: Boolean
        val post: Post
        val comments: List<Comment>

        data class Initial(
            override val id: Int,
            override val page: Int,
            override val canLoadMore: Boolean,
            override val post: Post,
            override val comments: List<Comment>,
        ) : Stable

        data class Loading(
            override val id: Int,
            override val page: Int,
            override val canLoadMore: Boolean,
            override val post: Post,
            override val comments: List<Comment>,
            val type: Type,
        ) : Stable {
            enum class Type { REFRESH, LOAD_MORE }
        }

        data class Error(
            override val id: Int,
            override val page: Int,
            override val canLoadMore: Boolean,
            override val post: Post,
            override val comments: List<Comment>,
            val cause: Throwable? = null,
        ) : Stable
    }

    data class Error(
        override val id: Int,
        val cause: Throwable? = null,
    ) : PostDetailState
}
