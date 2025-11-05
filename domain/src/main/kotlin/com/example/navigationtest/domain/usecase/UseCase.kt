package com.example.navigationtest.domain.usecase

interface UseCase<A, R> {
    fun execute(argument: A): R
}

fun <R> UseCase<Unit, R>.execute() = execute(Unit)
