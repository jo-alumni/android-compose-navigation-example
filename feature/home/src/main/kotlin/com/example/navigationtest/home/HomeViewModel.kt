package com.example.navigationtest.home

import androidx.lifecycle.ViewModel
import com.example.navigationtest.core.ui.entity.Tweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            tweets =
                (1..50).map {
                    Tweet(
                        id = it,
                        name = "name$it",
                        content = "content$it",
                    )
                },
        ),
    )

    val uiState = _uiState.asStateFlow()
}
