package com.example.navigationtest.local.datastore.di

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
