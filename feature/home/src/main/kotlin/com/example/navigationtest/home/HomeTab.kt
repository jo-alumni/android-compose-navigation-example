package com.example.navigationtest.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.navigation_test.feature.home.R

enum class HomeTab {
    Recommended,
    Followee;

    val title
        @Composable
        get(): String {
            return when (this) {
                Recommended -> stringResource(R.string.recommended)
                Followee -> stringResource(R.string.followee)
            }
        }
}
