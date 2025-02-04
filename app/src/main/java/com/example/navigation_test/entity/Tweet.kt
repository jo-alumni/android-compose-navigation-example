package com.example.navigation_test.entity

import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    val id: Int,
    val name: String,
    val content: String,
)
