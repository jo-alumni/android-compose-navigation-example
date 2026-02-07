package com.example.navigationtest.di

import com.example.navigationtest.domain.usecase.GetProfileUseCase
import com.example.navigationtest.domain.usecase.GetProfileUseCaseExecutor
import com.example.navigationtest.domain.usecase.GetTweetListUseCase
import com.example.navigationtest.domain.usecase.GetTweetListUseCaseExecutor
import com.example.navigationtest.domain.usecase.GetTweetUseCase
import com.example.navigationtest.domain.usecase.GetTweetUseCaseExecutor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideGetProfileUseCase(): GetProfileUseCase {
        return GetProfileUseCaseExecutor()
    }

    @Provides
    fun provideGetTweetUseCase(): GetTweetUseCase {
        return GetTweetUseCaseExecutor()
    }

    @Provides
    fun provideGetTweetListUseCase(): GetTweetListUseCase {
        return GetTweetListUseCaseExecutor()
    }
}
