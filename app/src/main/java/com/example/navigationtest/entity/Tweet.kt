package com.example.navigationtest.entity

import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    val id: Int,
    val name: String,
    val content: String,
)
