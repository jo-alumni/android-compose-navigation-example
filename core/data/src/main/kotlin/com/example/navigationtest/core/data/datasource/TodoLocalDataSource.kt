package com.example.navigationtest.core.data.datasource

import com.example.navigationtest.core.data.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface TodoLocalDataSource {
    fun getTodos(): Flow<List<TodoModel>>
    fun getDoneTodos(): Flow<List<TodoModel>>
    fun getNotDoneTodos(): Flow<List<TodoModel>>
    fun getTodoById(id: Long): Flow<TodoModel?>
    suspend fun upsertTodo(todo: TodoModel)
}
