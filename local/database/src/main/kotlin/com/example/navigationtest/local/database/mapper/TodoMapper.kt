package com.example.navigationtest.local.database.mapper

import com.example.navigationtest.data.model.TodoModel
import com.example.navigationtest.local.database.app.entity.Todo

object TodoMapper {
    fun toDatabaseEntity(dataModel: TodoModel): Todo = Todo(
        id = dataModel.id,
        content = dataModel.content,
        isDone = dataModel.isDone,
    )

    fun toDataModel(databaseEntity: Todo): TodoModel = TodoModel(
        id = databaseEntity.id,
        content = databaseEntity.content,
        isDone = databaseEntity.isDone,
    )
}
