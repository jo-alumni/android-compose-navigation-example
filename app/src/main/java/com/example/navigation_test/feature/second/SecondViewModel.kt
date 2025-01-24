package com.example.navigation_test.feature.second

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.navigation_test.feature.second.state.SecondUiState

internal class SecondViewModel : ViewModel() {
    val uiState = SecondUiState()
    override fun onCleared() {
        Log.d("SecondViewModel", "onCleared")
        super.onCleared()
    }

    init {
        Log.d("SecondViewModel", "init")
    }
}