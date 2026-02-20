package com.example.navigationtest.data.di

import com.example.navigationtest.data.datarepository.CommentDataRepository
import com.example.navigationtest.data.datarepository.PostDataRepository
import com.example.navigationtest.data.datasource.AppApiDataSource
import com.example.navigationtest.domain.repository.CommentRepository
import com.example.navigationtest.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providePostRepository(
        appApiDataSource: AppApiDataSource,
    ): PostRepository = PostDataRepository(
        appApiDataSource = appApiDataSource,
    )

    @Provides
    fun provideCommentRepository(
        appApiDataSource: AppApiDataSource,
    ): CommentRepository = CommentDataRepository(
        appApiDataSource = appApiDataSource,
    )
}
