package com.example.navigationtest.domain.entity

object EntityFaker {
    fun fakeTweet() = Tweet(
        id = 1,
        content = "This is a sample tweet content for the preview.",
        postUser = fakeProfile(),
    )

    fun fakeProfile() = Profile(
        id = "sample_user",
        name = "Sample User",
        description = "This is a sample user description.",
    )
}
