package com.example.navigationtest.posts.contract

import com.example.navigationtest.core.util.State
import com.example.navigationtest.domain.entity.Post

internal sealed interface PostsState : State {
    val posts: List<Post>

    data class Initial(
        override val posts: List<Post> = emptyList(),
    ) : PostsState

    data class Loading(
        override val posts: List<Post>,
        val type: Type,
    ) : PostsState {
        enum class Type { REFRESH, LOAD_MORE }
    }

    data class Stable(
        override val posts: List<Post>,
    ) : PostsState

    data class Error(
        override val posts: List<Post>,
        val cause: Throwable? = null,
    ) : PostsState
}
