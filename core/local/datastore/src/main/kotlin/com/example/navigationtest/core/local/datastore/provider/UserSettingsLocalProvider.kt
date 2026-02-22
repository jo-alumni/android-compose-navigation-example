package com.example.navigationtest.core.local.datastore.provider

import androidx.datastore.core.DataStore
import com.example.navigationtest.core.data.datasource.UserSettingsLocalDataSource
import com.example.navigationtest.core.data.model.UserSettingsModel
import com.example.navigationtest.core.local.datastore.mapper.UserSettingsMapper
import com.example.navigationtest.core.local.datastore.model.UserSettingsDatastoreModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserSettingsLocalProvider @Inject constructor(
    val datastore: DataStore<UserSettingsDatastoreModel>,
) : UserSettingsLocalDataSource {
    override fun getUserSettings(): Flow<UserSettingsModel> {
        return datastore.data.map(UserSettingsMapper::toDataModel)
    }

    override suspend fun setUserSettings(userSettings: UserSettingsModel) {
        datastore.updateData {
            userSettings.let(UserSettingsMapper::toDataStoreModel)
        }
    }
}
