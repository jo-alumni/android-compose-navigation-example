package com.example.navigation_test.feature.first

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.navigation_test.feature.first.state.FirstUiState

class FirstViewModel : ViewModel() {
    val uiState = FirstUiState()
    override fun onCleared() {
        Log.d("FirstViewModel", "onCleared")
        super.onCleared()
    }

    init {
        Log.d("FirstViewModel", "init")
    }
}