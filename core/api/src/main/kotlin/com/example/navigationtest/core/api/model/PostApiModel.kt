package com.example.navigationtest.core.api.model

import kotlinx.serialization.Serializable

@Serializable
data class PostApiModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)
