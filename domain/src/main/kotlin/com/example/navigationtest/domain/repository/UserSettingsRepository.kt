package com.example.navigationtest.domain.repository

import com.example.navigationtest.domain.entity.UserSettings
import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {
    fun getUserSettings(): Flow<UserSettings>

    suspend fun setUserSettings(userSettings: UserSettings)
}
