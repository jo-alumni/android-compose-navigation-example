package com.example.navigationtest.home

import androidx.lifecycle.ViewModel
import com.example.navigationtest.core.ui.entity.Tweet

class HomeViewModel : ViewModel() {
    val uiState =
        HomeUiState(
            tweets =
                (1..50).map {
                    Tweet(
                        id = it,
                        name = "name$it",
                        content = "content$it",
                    )
                },
        )
}
