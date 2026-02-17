package com.example.navigationtest.posts

import androidx.lifecycle.viewModelScope
import com.example.navigationtest.core.util.ContractedViewModel
import com.example.navigationtest.domain.repository.PostRepository
import com.example.navigationtest.posts.contract.PostsEvent
import com.example.navigationtest.posts.contract.PostsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PostsViewModel @Inject constructor(
    private val postRepository: PostRepository,
) : ContractedViewModel<PostsState, PostsEvent>(
    initialState = PostsState.Initial(emptyList()),
) {
    fun refresh() {
        viewModelScope.launch {
            _uiState.update { state -> PostsState.Loading(posts = state.posts) }
            runCatching {
                postRepository.getPosts(page = 1, POSTS_LIMIT)
            }.fold(
                onSuccess = {
                    _uiState.emit(PostsState.Stable(posts = it))
                    _uiEvent.emit(PostsEvent.ShowSnackbar("Success"))
                },
                onFailure = { cause ->
                    _uiState.update { state -> PostsState.Error(posts = state.posts, cause = cause) }
                },
            )
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            _uiState.update { state -> PostsState.Loading(posts = state.posts) }
            runCatching {
                val nextPage = (currentState.posts.size / POSTS_LIMIT) + 1
                postRepository.getPosts(page = nextPage, POSTS_LIMIT)
            }.fold(
                onSuccess = { newPosts ->
                    _uiState.emit(PostsState.Stable(posts = currentState.posts + newPosts))
                    _uiEvent.emit(PostsEvent.ShowSnackbar("Success"))
                },
                onFailure = { cause ->
                    _uiState.update { state -> PostsState.Error(posts = state.posts, cause = cause) }
                },
            )
        }
    }

    init {
        refresh()
    }

    companion object {
        private const val POSTS_LIMIT = 10
    }
}
