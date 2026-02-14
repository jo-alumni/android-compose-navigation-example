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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PostDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postRepository: PostRepository,
) : ContractedViewModel<PostDetailState, PostDetailEvent>(
    initialState = PostDetailState.Loading(savedStateHandle.toRoute<PostDetailDetailDestination>().id),
) {
    fun load() = viewModelScope.launch {
        mutableUiState.update { state -> PostDetailState.Loading(state.id) }
        runCatching {
            postRepository.getPost(currentState.id)
        }.fold(
            onSuccess = {
                mutableUiState.update { state ->
                    PostDetailState.Success(id = state.id, post = it)
                }
            },
            onFailure = {
                mutableUiState.update { state ->
                    PostDetailState.Error(id = state.id, cause = it)
                }
            },
        )
    }

    init {
        load()
    }
}
