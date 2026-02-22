package com.example.navigationtest.core.local.database.di

import android.content.Context
import androidx.room.Room
import com.example.navigationtest.core.data.datasource.TodoLocalDataSource
import com.example.navigationtest.core.local.database.app.AppDatabase
import com.example.navigationtest.core.local.database.app.dao.TodoDao
import com.example.navigationtest.core.local.database.provider.TodoLocalProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalProvideModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder<AppDatabase>(
        context = context,
        name = "app_database",
    ).build()

    @Provides
    @Singleton
    fun provideTodoDao(
        appDatabase: AppDatabase,
    ): TodoDao = appDatabase.todoDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalBindModule {
    @Binds
    @Singleton
    abstract fun bindTodoLocalDataSource(provider: TodoLocalProvider): TodoLocalDataSource
}
