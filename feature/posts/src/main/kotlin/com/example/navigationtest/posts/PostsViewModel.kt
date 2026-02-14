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
    initialState = PostsState.Loading(emptyList()),
) {
    fun load() = viewModelScope.launch {
        mutableUiState.update { state -> PostsState.Loading(posts = state.posts) }
        runCatching {
            postRepository.getPosts()
        }.fold(
            onSuccess = {
                mutableUiState.update { _ -> PostsState.Success(posts = it) }
                mutableUiEvent.emit(PostsEvent.ShowSnackbar("Success"))
            },
            onFailure = {
                mutableUiState.update { state -> PostsState.Error(posts = state.posts, cause = it) }
            },
        )
    }

    init {
        load()
    }
}
