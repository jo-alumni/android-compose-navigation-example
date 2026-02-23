package com.example.navigationtest.core.data.datarepository

import com.example.navigationtest.core.data.datasource.TodoLocalDataSource
import com.example.navigationtest.core.data.mapper.local.TodoMapper
import com.example.navigationtest.core.domain.entity.Todo
import com.example.navigationtest.core.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoDataRepository @Inject constructor(
    private val todoLocalDataSource: TodoLocalDataSource,
) : TodoRepository {
    override fun getAll(): Flow<List<Todo>> =
        todoLocalDataSource
            .getTodos()
            .map { it.map(TodoMapper::toEntity) }

    override fun getDone(): Flow<List<Todo>> =
        todoLocalDataSource
            .getDoneTodos()
            .map { it.map(TodoMapper::toEntity) }

    override fun getNotDone(): Flow<List<Todo>> =
        todoLocalDataSource
            .getNotDoneTodos()
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
