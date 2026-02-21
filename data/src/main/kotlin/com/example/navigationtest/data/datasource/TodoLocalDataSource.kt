package com.example.navigationtest.data.datasource

import com.example.navigationtest.data.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface TodoLocalDataSource {
    fun getTodos(): Flow<List<TodoModel>>
    fun getTodoById(id: Long): Flow<TodoModel?>
    suspend fun upsertTodo(todo: TodoModel)
}
