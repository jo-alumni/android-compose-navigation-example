package com.example.navigationtest.core.data.mapper.local

import com.example.navigationtest.core.data.model.TodoModel
import com.example.navigationtest.core.domain.entity.Todo

object TodoMapper {
    fun toEntity(todo: TodoModel): Todo = Todo(
        id = todo.id,
        content = todo.content,
        isDone = todo.isDone,
    )

    fun toDataModel(todo: Todo): TodoModel = TodoModel(
        id = todo.id,
        content = todo.content,
        isDone = todo.isDone,
    )
}
