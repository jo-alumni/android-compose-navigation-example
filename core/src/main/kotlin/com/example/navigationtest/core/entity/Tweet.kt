package com.example.navigationtest.core.entity

import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    val id: Int,
    val content: String,
    val postUser: Profile,
)
