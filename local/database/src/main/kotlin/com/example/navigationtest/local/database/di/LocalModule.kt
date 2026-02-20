package com.example.navigationtest.local.database.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalProvideModule {
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalBindModule {
}
