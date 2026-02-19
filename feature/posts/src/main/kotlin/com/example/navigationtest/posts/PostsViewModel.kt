package com.example.navigationtest.posts

import androidx.lifecycle.ViewModel
import com.example.navigationtest.core.extension.toUnit
import com.example.navigationtest.domain.repository.PostRepository
import com.example.navigationtest.posts.contract.PostsEvent
import com.example.navigationtest.posts.contract.PostsState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
internal class PostsViewModel @Inject constructor(
    private val postRepository: PostRepository,
) : ViewModel(), ContainerHost<PostsState, PostsEvent> {
    override val container: Container<PostsState, PostsEvent> = container(
        initialState = PostsState.Initial(),
    ) {
        refresh()
    }

    fun refresh() = intent {
        reduce {
            PostsState.Loading(
                posts = state.posts,
                type = PostsState.Loading.Type.REFRESH,
                page = 1,
                canLoadMore = true,
            )
        }
        runCatching {
            postRepository.getPosts(page = 1, PostsState.POSTS_PAGE_LIMIT)
        }.fold(
            onSuccess = { posts ->
                reduce {
                    PostsState.Stable(
                        posts = posts,
                        page = 1,
                        canLoadMore = posts.size >= PostsState.POSTS_PAGE_LIMIT,
                    )
                }
                postSideEffect(PostsEvent.ShowSnackbar.Success)
            },
            onFailure = { cause ->
                reduce {
                    PostsState.Error(
                        posts = state.posts,
                        page = 1,
                        canLoadMore = state.canLoadMore,
                        cause = cause,
                    )
                }
                postSideEffect(PostsEvent.ShowSnackbar.Error)
            },
        )
    }.toUnit

    fun loadMore() = intent {
        if (state is PostsState.Loading || !state.canLoadMore) return@intent
        val currentPage = state.page
        reduce {
            PostsState.Loading(
                posts = state.posts,
                type = PostsState.Loading.Type.LOAD_MORE,
                page = currentPage,
                canLoadMore = state.canLoadMore,
            )
        }
        runCatching {
            postRepository.getPosts(page = currentPage + 1, PostsState.POSTS_PAGE_LIMIT)
        }.fold(
            onSuccess = { posts ->
                reduce {
                    PostsState.Stable(
                        posts = state.posts + posts,
                        page = currentPage + 1,
                        canLoadMore = posts.size >= PostsState.POSTS_PAGE_LIMIT,
                    )
                }
            },
            onFailure = { cause ->
                reduce {
                    PostsState.Error(
                        posts = state.posts,
                        page = currentPage,
                        canLoadMore = state.canLoadMore,
                        cause = cause,
                    )
                }
                postSideEffect(PostsEvent.ShowSnackbar.Error)
            },
        )
    }.toUnit
}
