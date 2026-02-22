package com.example.navigationtest.core.data.datasource

import com.example.navigationtest.core.data.model.UserSettingsModel
import kotlinx.coroutines.flow.Flow

interface UserSettingsLocalDataSource {
    fun getUserSettings(): Flow<UserSettingsModel>

    suspend fun setUserSettings(userSettings: UserSettingsModel)
}
