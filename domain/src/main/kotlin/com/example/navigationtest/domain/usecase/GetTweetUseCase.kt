package com.example.navigationtest.domain.usecase

import com.example.navigationtest.domain.entity.Tweet
import kotlinx.coroutines.delay

interface GetTweetUseCase : UseCase<GetTweetUseCase.Args, Tweet> {
    data class Args(val id: String)
}

class GetTweetUseCaseExecutor() : GetTweetUseCase {
    override suspend fun execute(argument: GetTweetUseCase.Args): Tweet {
        delay(1000)
        return Tweet.fake(argument.id)
    }
}
