package com.example.navigationtest.domain.usecase

import com.example.navigationtest.domain.entity.Profile
import kotlinx.coroutines.delay

interface GetProfileUseCase : UseCase<GetProfileUseCase.Args, Profile> {
    data class Args(val id: String)
}

class GetProfileUseCaseImpl : GetProfileUseCase {
    override suspend fun execute(argument: GetProfileUseCase.Args): Profile {
        delay(1000)
        return Profile.fake(argument.id)
    }
}
