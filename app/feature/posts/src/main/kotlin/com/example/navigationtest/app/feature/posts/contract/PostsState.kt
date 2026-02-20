package com.example.navigationtest.app.feature.posts.contract

import com.example.navigationtest.app.core.util.State
import com.example.navigationtest.domain.entity.Post

internal sealed interface PostsState : State {
    val posts: List<Post>
    val page: Int
    val canLoadMore: Boolean

    data class Initial(
        override val posts: List<Post> = emptyList(),
        override val page: Int = 1,
        override val canLoadMore: Boolean = true,
    ) : PostsState

    data class Loading(
        override val posts: List<Post>,
        override val page: Int,
        override val canLoadMore: Boolean,
        val type: Type,
    ) : PostsState {
        enum class Type { REFRESH, LOAD_MORE }
    }

    data class Stable(
        override val posts: List<Post>,
        override val page: Int,
        override val canLoadMore: Boolean,
    ) : PostsState

    data class Error(
        override val posts: List<Post>,
        override val page: Int,
        val cause: Throwable? = null,
        override val canLoadMore: Boolean,
    ) : PostsState

    companion object {
        const val POSTS_PAGE_LIMIT = 10
    }
}
