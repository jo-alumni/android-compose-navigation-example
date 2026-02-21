package com.example.navigationtest.local.database.app.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.navigationtest.local.database.app.entity.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAll(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getById(id: Long): Flow<Todo?>

    @Upsert
    suspend fun upsert(todo: Todo)
}
