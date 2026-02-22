package com.example.navigationtest.local.database.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.navigationtest.local.database.app.dao.TodoDao
import com.example.navigationtest.local.database.app.entity.Todo

@Database(
    entities = [
        Todo::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
