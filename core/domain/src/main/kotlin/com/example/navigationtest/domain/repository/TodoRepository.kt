package com.example.navigationtest.domain.repository

import com.example.navigationtest.domain.entity.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAll(): Flow<List<Todo>>
    fun getById(id: Long): Flow<Todo?>
    suspend fun upsert(todo: Todo)
}
