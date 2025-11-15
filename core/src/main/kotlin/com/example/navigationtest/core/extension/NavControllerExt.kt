package com.example.navigationtest.core.extension

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun NavController.LoggingBackStacks() {
    val backStack by currentBackStack.collectAsStateWithLifecycle()
    val filteredBackStack = backStack.filter { it.destination.route != null }
    LaunchedEffect(filteredBackStack) {
        if (filteredBackStack.isEmpty()) return@LaunchedEffect
        Log.d(
            "NavController",
            buildString {
                appendLine("--- Full Back Stack ---")
                filteredBackStack.forEachIndexed { index, entry -> appendLine("Entry $index: ${entry.destination.route}") }
                appendLine("-----------------------")
            },
        )
    }
}

