package com.example.navigationtest.di

import com.example.navigationtest.domain.usecase.GetProfileUseCase
import com.example.navigationtest.domain.usecase.GetProfileUseCaseImpl
import com.example.navigationtest.domain.usecase.GetTweetListUseCase
import com.example.navigationtest.domain.usecase.GetTweetListUseCaseImpl
import com.example.navigationtest.domain.usecase.GetTweetUseCase
import com.example.navigationtest.domain.usecase.GetTweetUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideGetProfileUseCase(): GetProfileUseCase {
        return GetProfileUseCaseImpl()
    }

    @Provides
    fun provideGetTweetUseCase(): GetTweetUseCase {
        return GetTweetUseCaseImpl()
    }

    @Provides
    fun provideGetTweetListUseCase(): GetTweetListUseCase {
        return GetTweetListUseCaseImpl()
    }
}
