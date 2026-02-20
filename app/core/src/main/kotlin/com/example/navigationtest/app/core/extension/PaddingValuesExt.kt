package com.example.navigationtest.app.core.extension

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp

@Composable
fun PaddingValues.copy(
    top: Dp? = null,
    bottom: Dp? = null,
    start: Dp? = null,
    end: Dp? = null,
): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        top = top ?: this.calculateTopPadding(),
        bottom = bottom ?: this.calculateBottomPadding(),
        start = start ?: this.calculateStartPadding(layoutDirection),
        end = end ?: this.calculateEndPadding(layoutDirection),
    )
}
