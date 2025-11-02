package com.example.navigationtest.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: String,
    val name: String,
    val description: String,
)
