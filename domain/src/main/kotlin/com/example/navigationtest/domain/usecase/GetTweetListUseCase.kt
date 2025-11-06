package com.example.navigationtest.domain.usecase

import com.example.navigationtest.domain.entity.Tweet

interface GetTweetListUseCase : UseCase<Unit, List<Tweet>>

class GetTweetListUseCaseImpl() : GetTweetListUseCase {
    override suspend fun execute(argument: Unit): List<Tweet> = (1..50).map {
        Tweet.fake().copy(id = it.toString())
    }
}
