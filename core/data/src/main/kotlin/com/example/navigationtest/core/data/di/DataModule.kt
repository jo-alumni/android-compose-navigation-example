package com.example.navigationtest.core.data.di

import com.example.navigationtest.core.data.datarepository.CommentDataRepository
import com.example.navigationtest.core.data.datarepository.PostDataRepository
import com.example.navigationtest.core.data.datarepository.TodoDataRepository
import com.example.navigationtest.core.data.datarepository.UseSettingsDataRepository
import com.example.navigationtest.core.domain.repository.CommentRepository
import com.example.navigationtest.core.domain.repository.PostRepository
import com.example.navigationtest.core.domain.repository.TodoRepository
import com.example.navigationtest.core.domain.repository.UserSettingsRepository
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

    @Binds
    @Singleton
    abstract fun bindTodoRepository(todoDataRepository: TodoDataRepository): TodoRepository
}
