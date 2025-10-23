package com.example.navigationtest.core.ui.entity

import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    val id: Int,
    val name: String,
    val content: String,
)
