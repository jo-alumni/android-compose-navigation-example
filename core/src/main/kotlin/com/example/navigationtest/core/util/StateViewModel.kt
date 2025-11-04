package com.example.navigationtest.core.util

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class StateViewModel<S : State, E : Event>(initialState: S) : ViewModel() {
    protected val mutableUiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = mutableUiState.asStateFlow()
    protected val mutableUiEvent = MutableSharedFlow<E>()
    val uiEvent = mutableUiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            launch { uiState.collect { uiState -> Log.d(this@StateViewModel.javaClass.simpleName, "uiState: $uiState") } }
            launch { uiEvent.collect { uiEvent -> Log.d(this@StateViewModel.javaClass.simpleName, "uiEvent: $uiEvent") } }
        }
    }
}
