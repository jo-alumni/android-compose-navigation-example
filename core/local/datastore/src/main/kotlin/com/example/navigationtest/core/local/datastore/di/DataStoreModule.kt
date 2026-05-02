package com.example.navigationtest.core.local.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.example.navigationtest.core.data.datasource.UserSettingsLocalDataSource
import com.example.navigationtest.core.local.datastore.model.UserSettingsDatastoreModel
import com.example.navigationtest.core.local.datastore.model.UserSettingsSerializer
import com.example.navigationtest.core.local.datastore.provider.UserSettingsLocalProvider
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
abstract class DataStoreModule {
    @Binds
    @Singleton
    abstract fun bindUserSettingsLocalProvider(provider: UserSettingsLocalProvider): UserSettingsLocalDataSource

    companion object {
        @Provides
        @Singleton
        fun provideUserPreferencesDataStore(
            @ApplicationContext context: Context,
        ): DataStore<UserSettingsDatastoreModel> = DataStoreFactory.createInDeviceProtectedStorage(
            context = context,
            fileName = USER_SETTINGS,
            serializer = UserSettingsSerializer,
        )
    }
}
