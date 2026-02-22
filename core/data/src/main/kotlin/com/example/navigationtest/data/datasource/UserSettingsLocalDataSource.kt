package com.example.navigationtest.data.datasource

import com.example.navigationtest.data.model.UserSettingsModel
import kotlinx.coroutines.flow.Flow

interface UserSettingsLocalDataSource {
    fun getUserSettings(): Flow<UserSettingsModel>

    suspend fun setUserSettings(userSettings: UserSettingsModel)
}
