package com.example.navigationtest.core.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

@SuppressLint("ComposableNaming")
@Composable
inline fun <reified T : State> Any.render(content: @Composable T.() -> Unit) {
    if (this is T) {
        content()
    }
}
