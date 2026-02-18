package com.example.navigationtest.postDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationtest.core.util.ContractedViewModel
import com.example.navigationtest.domain.repository.PostRepository
import com.example.navigationtest.postDetail.contract.PostDetailEvent
import com.example.navigationtest.postDetail.contract.PostDetailState
import com.example.navigationtest.postDetail.navigation.PostDetailDetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PostDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postRepository: PostRepository,
) : ContractedViewModel<PostDetailState, PostDetailEvent>(
    initialState = PostDetailState.Initial(savedStateHandle.toRoute<PostDetailDetailDestination>().id),
) {

    fun refresh() = viewModelScope.launch {
        val state = _uiState.updateAndGet {
            if (it !is PostDetailState.Stable) return@updateAndGet it
            PostDetailState.Stable.Loading(
                id = it.id,
                page = 1,
                post = it.post,
                comments = it.comments,
                type = PostDetailState.Stable.Loading.Type.REFRESH,
            )
        }
        if (state !is PostDetailState.Stable.Loading) return@launch
        runCatching {
            val post = async { postRepository.getPost(currentState.id) }
            val comments = async { postRepository.getComments(postId = currentState.id, page = 1, limit = COMMENTS_LIMIT) }
            return@runCatching post.await() to comments.await()
        }.fold(
            onSuccess = { (post, comments) ->
                _uiState.update { state ->
                    PostDetailState.Stable.Initial(id = state.id, page = 1, post = post, comments = comments)
                }
            },
            onFailure = {
                _uiState.update { state ->
                    PostDetailState.Error(id = state.id, cause = it)
                }
            },
        )
    }

    fun loadMoreComments() = viewModelScope.launch {
        val state = _uiState.updateAndGet {
            if (it !is PostDetailState.Stable) return@updateAndGet it
            PostDetailState.Stable.Loading(
                id = it.id,
                post = it.post,
                comments = it.comments,
                page = it.page,
                type = PostDetailState.Stable.Loading.Type.LOAD_MORE,
            )
        }
        if (state !is PostDetailState.Stable.Loading) return@launch
        val nextPage = (state.comments.size / COMMENTS_LIMIT) + 1
        runCatching {
            postRepository.getComments(postId = state.id, page = nextPage, limit = COMMENTS_LIMIT)
        }.fold(
            onSuccess = { comments ->
                _uiState.update { state ->
                    if (state !is PostDetailState.Stable) return@update state
                    PostDetailState.Stable.Initial(
                        id = state.id,
                        post = state.post,
                        comments = comments,
                        page = nextPage,
                    )
                }
            },
            onFailure = {
                _uiState.update { state ->
                    if (state !is PostDetailState.Stable) return@update state
                    PostDetailState.Stable.Error(
                        id = state.id,
                        post = state.post,
                        comments = state.comments,
                        page = nextPage,
                    )
                }
            },
        )
    }

    private fun init() = viewModelScope.launch {
        _uiState.update { state ->
            PostDetailState.Loading(
                id = state.id,
            )
        }
        runCatching {
            val post = async { postRepository.getPost(currentState.id) }
            val comments = async { postRepository.getComments(postId = currentState.id, page = 1, limit = COMMENTS_LIMIT) }
            return@runCatching post.await() to comments.await()
        }.fold(
            onSuccess = { (post, comments) ->
                _uiState.update { state ->
                    PostDetailState.Stable.Initial(
                        id = state.id,
                        post = post,
                        comments = comments,
                        page = 1,
                    )
                }
            },
            onFailure = {
                _uiState.update { state ->
                    PostDetailState.Error(id = state.id, cause = it)
                }
            },
        )
    }

    init {
        init()
    }

    companion object {
        private const val COMMENTS_LIMIT = 10
    }
}
