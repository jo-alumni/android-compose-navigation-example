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
    protected val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()
    protected val _uiEvent = MutableSharedFlow<E>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            uiState.collect { uiState -> Log.d(this@StateViewModel.javaClass.simpleName, "uiState: $uiState") }
            uiEvent.collect { uiEvent -> Log.d(this@StateViewModel.javaClass.simpleName, "uiEvent: $uiEvent") }
        }
    }
}
