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
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
internal class PostDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postRepository: PostRepository,
) : ContractedViewModel<PostDetailState, PostDetailEvent>(
    initialState = PostDetailState.Initial(savedStateHandle.toRoute<PostDetailDetailDestination>().id),
) {
    private val mutex = Mutex()

    fun refresh() = viewModelScope.launch {
        mutex.withLock {
            val state = _uiState.updateAndGet {
                if (it !is PostDetailState.Stable) return@updateAndGet it
                PostDetailState.Stable.Loading(
                    id = it.id,
                    page = it.page,
                    canLoadMore = it.canLoadMore,
                    post = it.post,
                    comments = it.comments,
                    type = PostDetailState.Stable.Loading.Type.REFRESH,
                )
            }
            if (state !is PostDetailState.Stable.Loading) return@launch
            runCatching {
                val post = async { postRepository.getPost(currentState.id) }
                val comments = async { postRepository.getComments(postId = currentState.id, page = 1, limit = COMMENTS_PAGE_LIMIT) }
                return@runCatching post.await() to comments.await()
            }.fold(
                onSuccess = { (post, comments) ->
                    _uiState.update { state ->
                        PostDetailState.Stable.Initial(
                            id = state.id,
                            page = 1,
                            post = post,
                            comments = comments,
                            canLoadMore = comments.size >= COMMENTS_PAGE_LIMIT,
                        )
                    }
                },
                onFailure = { cause ->
                    _uiState.update { state ->
                        PostDetailState.Error(id = state.id, cause = cause)
                    }
                },
            )
        }
    }

    fun loadMoreComments() = viewModelScope.launch {
        mutex.withLock {
            val state = _uiState.updateAndGet {
                if (it !is PostDetailState.Stable) return@updateAndGet it
                PostDetailState.Stable.Loading(
                    id = it.id,
                    post = it.post,
                    comments = it.comments,
                    page = it.page,
                    type = PostDetailState.Stable.Loading.Type.LOAD_MORE,
                    canLoadMore = it.canLoadMore,
                )
            }
            if (state !is PostDetailState.Stable.Loading) return@launch
            val nextPage = state.page + 1
            runCatching {
                postRepository.getComments(postId = state.id, page = nextPage, limit = COMMENTS_PAGE_LIMIT)
            }.fold(
                onSuccess = { comments ->
                    _uiState.update { state ->
                        if (state !is PostDetailState.Stable) return@update state
                        PostDetailState.Stable.Initial(
                            id = state.id,
                            post = state.post,
                            comments = state.comments + comments,
                            page = nextPage,
                            canLoadMore = comments.size >= COMMENTS_PAGE_LIMIT,
                        )
                    }
                },
                onFailure = { cause ->
                    _uiState.update { state ->
                        if (state !is PostDetailState.Stable) return@update state
                        PostDetailState.Stable.Error(
                            id = state.id,
                            post = state.post,
                            comments = state.comments,
                            page = state.page,
                            canLoadMore = state.canLoadMore,
                            cause = cause,
                        )
                    }
                },
            )
        }
    }

    private fun init() = viewModelScope.launch {
        mutex.withLock {
            _uiState.update { state ->
                PostDetailState.Loading(
                    id = state.id,
                )
            }
            runCatching {
                val post = async { postRepository.getPost(currentState.id) }
                val comments = async { postRepository.getComments(postId = currentState.id, page = 1, limit = COMMENTS_PAGE_LIMIT) }
                return@runCatching post.await() to comments.await()
            }.fold(
                onSuccess = { (post, comments) ->
                    _uiState.update { state ->
                        PostDetailState.Stable.Initial(
                            id = state.id,
                            post = post,
                            comments = comments,
                            page = 1,
                            canLoadMore = comments.size >= COMMENTS_PAGE_LIMIT,
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
    }

    init {
        init()
    }

    companion object {
        private const val COMMENTS_PAGE_LIMIT = 10
    }
}
