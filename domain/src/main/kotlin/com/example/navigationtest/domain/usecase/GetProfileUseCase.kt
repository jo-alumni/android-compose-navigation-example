package com.example.navigationtest.domain.usecase

import com.example.navigationtest.domain.entity.Profile

interface GetProfileUseCase : UseCase<GetProfileUseCase.Args, Profile> {
    data class Args(val id: String)
}

class GetProfileUseCaseImpl : GetProfileUseCase {
    override suspend fun execute(argument: GetProfileUseCase.Args): Profile =
        Profile.fake().copy(id = argument.id)
}
