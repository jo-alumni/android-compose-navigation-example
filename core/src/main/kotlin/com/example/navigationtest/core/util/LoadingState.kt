package com.example.navigationtest.core.util

sealed interface LoadingState<out T> {
    data class Success<T>(val data: T) : LoadingState<T>
    data object Failure : LoadingState<Nothing>
    data object Loading : LoadingState<Nothing>
}
