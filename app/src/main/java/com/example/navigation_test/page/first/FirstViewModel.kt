package com.example.navigation_test.page.first

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.navigation_test.page.first.state.FirstUiState

internal class FirstViewModel : ViewModel() {
    val uiState = FirstUiState()
    override fun onCleared() {
        Log.d("FirstViewModel", "onCleared")
        super.onCleared()
    }

    init {
        Log.d("FirstViewModel", "init")
    }
}