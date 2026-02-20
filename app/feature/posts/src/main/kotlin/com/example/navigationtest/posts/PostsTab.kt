package com.example.navigationtest.posts

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.navigation_test.app.feature.posts.R

enum class PostsTab {
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
