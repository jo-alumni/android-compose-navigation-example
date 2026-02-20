package com.example.navigationtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CommentModel(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)
