package com.example.navigationtest.app.feature.todo.contract

import com.example.navigationtest.app.core.util.State
import com.example.navigationtest.core.domain.entity.Todo

data class TodoState(
    val todos: List<Todo>,
    val input: String,
) : State
