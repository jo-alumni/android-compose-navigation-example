package com.example.navigationtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)
