package com.example.navigationtest.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    val id: String,
    val content: String,
    val postUser: Profile,
) {
    companion object {
        fun fake(suffix: String? = null) = if (suffix == null) {
            Tweet(
                id = "id",
                content = "Hello World",
                postUser = Profile.fake(),
            )
        } else {
            Tweet(
                id = "id_$suffix",
                content = "Hello World $suffix",
                postUser = Profile.fake(suffix),
            )
        }
    }
}
