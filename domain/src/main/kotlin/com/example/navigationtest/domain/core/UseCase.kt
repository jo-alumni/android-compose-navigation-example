package com.example.navigationtest.domain.core

interface UseCase<A, R> {
    suspend fun execute(argument: A): R
}

suspend fun <R> UseCase<Unit, R>.execute() = execute(Unit)
