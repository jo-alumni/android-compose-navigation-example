package com.example.navigationtest.api.di

import com.example.navigationtest.api.core.HttpClientHelper.createHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import jakarta.inject.Singleton
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideHttpClient(@ApiBaseUrl baseUrl: String): HttpClient = createHttpClient(baseUrl)
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiBaseUrl
