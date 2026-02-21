package com.example.navigationtest.local.database.app.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey val id: Long,
    val content: String,
    val isDone: Boolean,
)
