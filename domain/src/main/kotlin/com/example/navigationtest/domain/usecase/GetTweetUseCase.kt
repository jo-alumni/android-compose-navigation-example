package com.example.navigationtest.domain.usecase

import com.example.navigationtest.domain.entity.Tweet

interface GetTweetUseCase : UseCase<GetTweetUseCase.Args, Tweet> {
    data class Args(val id: Int)
}

class GetTweetUseCaseImpl() : GetTweetUseCase {
    override suspend fun execute(argument: GetTweetUseCase.Args): Tweet =
        Tweet.fake().copy(id = argument.id)
}
