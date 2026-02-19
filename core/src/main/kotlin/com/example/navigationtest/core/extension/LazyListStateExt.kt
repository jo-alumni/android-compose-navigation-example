package com.example.navigationtest.core.extension

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow

@Composable
fun LazyListState.OnBottomReached(
    block: () -> Unit,
) {
    LaunchedEffect(this) {
        snapshotFlow {
            val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val totalItemsCount = layoutInfo.totalItemsCount
            (lastVisibleItemIndex != null && lastVisibleItemIndex >= totalItemsCount - 1)
        }.collect { isReachedEnd ->
            if (isReachedEnd) block()
        }
    }
}

