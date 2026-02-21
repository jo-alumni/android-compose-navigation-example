package com.example.navigationtest.app.feature.todo

import androidx.lifecycle.ViewModel
import com.example.navigationtest.app.feature.todo.contract.TodoEvent
import com.example.navigationtest.app.feature.todo.contract.TodoState
import com.example.navigationtest.core.extension.toUnit
import com.example.navigationtest.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
internal class TodoViewModel @Inject constructor(
    private val postRepository: PostRepository,
) : ViewModel(), ContainerHost<TodoState, TodoEvent> {
    override val container: Container<TodoState, TodoEvent> = container(
        initialState = TodoState.Initial(),
    ) {
        refresh()
    }

    fun refresh() = intent {
        reduce {
            TodoState.Loading(
                posts = state.posts,
                type = TodoState.Loading.Type.REFRESH,
                page = 1,
                canLoadMore = true,
            )
        }
        runCatching {
            postRepository.getPosts(page = 1, TodoState.Companion.POSTS_PAGE_LIMIT)
        }.fold(
            onSuccess = { posts ->
                reduce {
                    TodoState.Stable(
                        posts = posts,
                        page = 1,
                        canLoadMore = posts.size >= TodoState.Companion.POSTS_PAGE_LIMIT,
                    )
                }
                postSideEffect(TodoEvent.ShowSnackbar.Success)
            },
            onFailure = { cause ->
                reduce {
                    TodoState.Error(
                        posts = state.posts,
                        page = 1,
                        canLoadMore = state.canLoadMore,
                        cause = cause,
                    )
                }
                postSideEffect(TodoEvent.ShowSnackbar.Error)
            },
        )
    }.toUnit

    fun loadMore() = intent {
        if (state is TodoState.Loading || !state.canLoadMore) return@intent
        val currentPage = state.page
        reduce {
            TodoState.Loading(
                posts = state.posts,
                type = TodoState.Loading.Type.LOAD_MORE,
                page = currentPage,
                canLoadMore = state.canLoadMore,
            )
        }
        runCatching {
            postRepository.getPosts(page = currentPage + 1, TodoState.Companion.POSTS_PAGE_LIMIT)
        }.fold(
            onSuccess = { posts ->
                reduce {
                    TodoState.Stable(
                        posts = state.posts + posts,
                        page = currentPage + 1,
                        canLoadMore = posts.size >= TodoState.Companion.POSTS_PAGE_LIMIT,
                    )
                }
            },
            onFailure = { cause ->
                reduce {
                    TodoState.Error(
                        posts = state.posts,
                        page = currentPage,
                        canLoadMore = state.canLoadMore,
                        cause = cause,
                    )
                }
                postSideEffect(TodoEvent.ShowSnackbar.Error)
            },
        )
    }.toUnit
}
