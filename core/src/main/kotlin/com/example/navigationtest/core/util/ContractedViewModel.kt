package com.example.navigationtest.core.util

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class ContractedViewModel<S : State, E : Event>(initialState: S) : ViewModel() {
    @Suppress("PropertyName")
    protected val _uiState: MutableStateFlow<S> = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()
    protected val currentState: S get() = _uiState.value

    @Suppress("PropertyName")
    protected val _uiEvent: MutableSharedFlow<E> = MutableSharedFlow()
    val uiEvent: SharedFlow<E> = _uiEvent.asSharedFlow()

    init {
        // Logging every state and event
        viewModelScope.launch {
            launch { uiState.collect { uiState -> Log.d("${this@ContractedViewModel.javaClass.simpleName}::uiState", "uiState: $uiState") } }
            launch { uiEvent.collect { uiEvent -> Log.d("${this@ContractedViewModel.javaClass.simpleName}::uiEvent", "uiEvent: $uiEvent") } }
        }
    }
}
