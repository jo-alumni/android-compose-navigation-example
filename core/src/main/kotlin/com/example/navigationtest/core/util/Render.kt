package com.example.navigationtest.core.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@SuppressLint("ComposableNaming")
@Composable
inline fun <reified T : State> Any.render(content: @Composable T.() -> Unit) {
    if (this is T) {
        content()
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun <S : State, E : Event> ContractedViewModel<S, E>.handleEvents(onEvent: suspend (E) -> Unit) {
    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            onEvent(event)
        }
    }
}
