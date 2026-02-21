package com.example.navigationtest.local.database.provider

import com.example.navigationtest.data.datasource.TodoLocalDataSource
import com.example.navigationtest.data.model.TodoModel
import com.example.navigationtest.local.database.app.dao.TodoDao
import com.example.navigationtest.local.database.mapper.TodoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoLocalProvider(
    private val todoDao: TodoDao,
) : TodoLocalDataSource {
    override fun getTodos(): Flow<List<TodoModel>> =
        todoDao.getAll().map { it.map(TodoMapper::toDataModel) }

    override fun getTodoById(id: Long): Flow<TodoModel?> =
        todoDao.getById(id).map { it?.let(TodoMapper::toDataModel) }

    override suspend fun upsertTodo(todo: TodoModel) {
        todoDao.upsert(todo.let(TodoMapper::toDatabaseEntity))
    }
}
