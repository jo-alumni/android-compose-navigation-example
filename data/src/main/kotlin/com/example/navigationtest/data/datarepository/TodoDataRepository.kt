package com.example.navigationtest.data.datarepository

import com.example.navigationtest.data.datasource.TodoLocalDataSource
import com.example.navigationtest.data.mapper.local.TodoMapper
import com.example.navigationtest.domain.entity.Todo
import com.example.navigationtest.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoDataRepository(
    private val todoLocalDataSource: TodoLocalDataSource,
) : TodoRepository {
    override fun getAll(): Flow<List<Todo>> =
        todoLocalDataSource
            .getTodos()
            .map { it.map(TodoMapper::toEntity) }

    override fun getById(id: Long): Flow<Todo?> =
        todoLocalDataSource
            .getTodoById(id)
            .map { it?.let(TodoMapper::toEntity) }

    override suspend fun upsert(todo: Todo) =
        todoLocalDataSource
            .upsertTodo(
                todo.let(TodoMapper::toDataModel),
            )
}
