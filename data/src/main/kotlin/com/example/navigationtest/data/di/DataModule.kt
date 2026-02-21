package com.example.navigationtest.data.di

import com.example.navigationtest.data.datarepository.CommentDataRepository
import com.example.navigationtest.data.datarepository.PostDataRepository
import com.example.navigationtest.data.datarepository.UseSettingsDataRepository
import com.example.navigationtest.domain.repository.CommentRepository
import com.example.navigationtest.domain.repository.PostRepository
import com.example.navigationtest.domain.repository.UserSettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindPostRepository(postDataRepository: PostDataRepository): PostRepository

    @Binds
    @Singleton
    abstract fun bindCommentRepository(commentDataRepository: CommentDataRepository): CommentRepository

    @Binds
    @Singleton
    abstract fun bindUserSettingsRepository(useSettingsDataRepository: UseSettingsDataRepository): UserSettingsRepository
}
