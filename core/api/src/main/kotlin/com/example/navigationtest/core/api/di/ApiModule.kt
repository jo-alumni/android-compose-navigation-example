package com.example.navigationtest.core.api.di

import com.example.navigationtest.core.api.core.HttpClientHelper.createHttpClient
import com.example.navigationtest.core.api.provider.AppApiDataProvider
import com.example.navigationtest.core.common.di.ApiBaseUrl
import com.example.navigationtest.core.data.datasource.AppApiDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiProvideModule {
    @Provides
    @Singleton
    fun provideHttpClient(@ApiBaseUrl baseUrl: String): HttpClient = createHttpClient(baseUrl)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiBindModule {
    @Binds
    @Singleton
    abstract fun bindPostApiDataSource(appApiDataProvider: AppApiDataProvider): AppApiDataSource
}
