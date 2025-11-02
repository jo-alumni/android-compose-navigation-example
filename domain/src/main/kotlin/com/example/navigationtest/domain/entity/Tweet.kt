package com.example.navigationtest.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    val id: Int,
    val content: String,
    val postUser: Profile,
)
