package com.example.navigationtest.local.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.navigationtest.data.datasource.UserSettingsLocalDataSource
import com.example.navigationtest.local.datastore.model.UserSettingsDatastoreModel
import com.example.navigationtest.local.datastore.model.UserSettingsSerializer
import com.example.navigationtest.local.datastore.provider.UserSettingsLocalProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val USER_SETTINGS = "user_settings.json"

@Module
@InstallIn(SingletonComponent::class)
object LocalProvideModule {
    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context,
    ): DataStore<UserSettingsDatastoreModel> = DataStoreFactory.create(
        serializer = UserSettingsSerializer,
        produceFile = { context.dataStoreFile(USER_SETTINGS) },
    )
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalBindModule {
    @Binds
    @Singleton
    abstract fun bindUserSettingsLocalProvider(provider: UserSettingsLocalProvider): UserSettingsLocalDataSource
}
