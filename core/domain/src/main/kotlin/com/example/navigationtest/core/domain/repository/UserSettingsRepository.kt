package com.example.navigationtest.core.domain.repository

import com.example.navigationtest.core.domain.entity.UserSettings
import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {
    fun getUserSettings(): Flow<UserSettings>

    suspend fun setUserSettings(userSettings: UserSettings)
}
