package com.example.navigationtest.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    val id: Int,
    val content: String,
    val postUser: Profile,
) {
    companion object {
        fun fake() = Tweet(
            id = 1,
            content = "Hello World",
            postUser = Profile.fake(),
        )
    }
}
