package com.example.navigationtest.domain.usecase

import com.example.navigationtest.domain.entity.Tweet
import kotlinx.coroutines.delay

interface GetTweetListUseCase : UseCase<Unit, List<Tweet>>

class GetTweetListUseCaseImpl() : GetTweetListUseCase {
    override suspend fun execute(argument: Unit): List<Tweet> {
        delay(1000)
        return (1..50).map { Tweet.fake(it.toString()) }
    }
}
