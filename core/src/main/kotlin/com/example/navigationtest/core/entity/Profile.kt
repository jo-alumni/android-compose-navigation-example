package com.example.navigationtest.core.entity

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: Int,
    val name: String,
    val description: String,
)
