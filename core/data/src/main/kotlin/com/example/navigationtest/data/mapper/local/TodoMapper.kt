package com.example.navigationtest.data.mapper.local

import com.example.navigationtest.data.model.TodoModel
import com.example.navigationtest.domain.entity.Todo

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
