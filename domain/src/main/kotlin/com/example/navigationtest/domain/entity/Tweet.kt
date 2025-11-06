package com.example.navigationtest.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    val id: String,
    val content: String,
    val postUser: Profile,
) {
    companion object {
        fun fake() = Tweet(
            id = "id",
            content = "Hello World",
            postUser = Profile.fake(),
        )
    }
}
