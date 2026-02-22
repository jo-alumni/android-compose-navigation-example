package com.example.navigationtest.core.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CommentApiModel(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)
