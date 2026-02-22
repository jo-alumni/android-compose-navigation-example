package com.example.navigationtest.core.local.database.app.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val content: String,
    val isDone: Boolean,
)
