package com.example.navigationtest.postDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.navigationtest.domain.repository.CommentRepository
import com.example.navigationtest.domain.repository.PostRepository
import com.example.navigationtest.postDetail.contract.PostDetailEvent
import com.example.navigationtest.postDetail.contract.PostDetailState
import com.example.navigationtest.postDetail.navigation.PostDetailDetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
internal class PostDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
) : ViewModel(), ContainerHost<PostDetailState, PostDetailEvent> {
    override val container = container<PostDetailState, PostDetailEvent>(
        initialState = PostDetailState.Loading(
            id = savedStateHandle.toRoute<PostDetailDetailDestination>().id,
        ),
    ) {
        init()
    }

    fun refresh() = intent {
        reduce {
            val currentState = state
            if (currentState !is PostDetailState.Stable) return@reduce state
            PostDetailState.Stable.Loading(
                id = currentState.id,
                page = currentState.page,
                canLoadMore = currentState.canLoadMore,
                post = currentState.post,
                comments = currentState.comments,
                type = PostDetailState.Stable.Loading.Type.REFRESH,
            )
        }
        val currentState = state
        if (currentState !is PostDetailState.Stable.Loading) return@intent
        runCatching {
            coroutineScope {
                val post = async { postRepository.getPost(postId = state.id) }
                val comments = async { commentRepository.getComments(postId = state.id, page = 1, limit = COMMENTS_PAGE_LIMIT) }
                return@coroutineScope post.await() to comments.await()
            }
        }.fold(
            onSuccess = { (post, comments) ->
                reduce {
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
                reduce {
                    PostDetailState.Stable.Error(
                        id = currentState.id,
                        page = currentState.page,
                        canLoadMore = currentState.canLoadMore,
                        post = currentState.post,
                        comments = currentState.comments,
                        cause = cause,
                    )
                }
            },
        )
    }

    fun loadMoreComments() = intent {
        reduce {
            val currentState = state
            if (currentState !is PostDetailState.Stable || !currentState.canLoadMore) return@reduce state
            PostDetailState.Stable.Loading(
                id = currentState.id,
                post = currentState.post,
                comments = currentState.comments,
                page = currentState.page,
                type = PostDetailState.Stable.Loading.Type.LOAD_MORE,
                canLoadMore = currentState.canLoadMore,
            )
        }
        val currentState = state
        if (currentState !is PostDetailState.Stable.Loading) return@intent
        val nextPage = currentState.page + 1
        runCatching {
            commentRepository.getComments(postId = state.id, page = nextPage, limit = COMMENTS_PAGE_LIMIT)
        }.fold(
            onSuccess = { comments ->
                reduce {
                    PostDetailState.Stable.Initial(
                        id = currentState.id,
                        post = currentState.post,
                        comments = currentState.comments + comments,
                        page = nextPage,
                        canLoadMore = comments.size >= COMMENTS_PAGE_LIMIT,
                    )
                }
            },
            onFailure = { cause ->
                reduce {
                    PostDetailState.Stable.Error(
                        id = currentState.id,
                        post = currentState.post,
                        comments = currentState.comments,
                        page = currentState.page,
                        canLoadMore = currentState.canLoadMore,
                        cause = cause,
                    )
                }
            },
        )
    }

    private fun init() = intent {
        reduce {
            PostDetailState.Loading(id = state.id)
        }
        runCatching {
            coroutineScope {
                val post = async { postRepository.getPost(state.id) }
                val comments = async { commentRepository.getComments(postId = state.id, page = 1, limit = COMMENTS_PAGE_LIMIT) }
                return@coroutineScope post.await() to comments.await()
            }
        }.fold(
            onSuccess = { (post, comments) ->
                reduce {
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
                reduce {
                    PostDetailState.Error(id = state.id, cause = it)
                }
            },
        )
    }

    companion object {
        private const val COMMENTS_PAGE_LIMIT = 10
    }
}
