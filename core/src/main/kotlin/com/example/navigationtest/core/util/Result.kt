package com.example.navigationtest.core.util

sealed interface Result<T> {
    data class Success<T>(val data: T) : Result<T>
    data object Failure : Result<Nothing>
    data object Loading : Result<Nothing>
}
