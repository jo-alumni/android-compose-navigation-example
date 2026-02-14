package com.example.navigationtest.posts.contract

import com.example.navigationtest.core.util.State
import com.example.navigationtest.domain.entity.Post

internal sealed interface PostsState : State {
    val posts: List<Post>

    data class Loading(
        override val posts: List<Post>,
    ) : PostsState

    data class Success(
        override val posts: List<Post>,
    ) : PostsState

    data class Error(
        override val posts: List<Post>,
        val cause: Throwable? = null,
    ) : PostsState
}
