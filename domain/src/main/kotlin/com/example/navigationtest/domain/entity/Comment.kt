package com.example.navigationtest.domain.entity

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)
