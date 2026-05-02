package com.example.navigationtest.core.data.datarepository

import com.example.navigationtest.core.data.datasource.UserSettingsLocalDataSource
import com.example.navigationtest.core.data.mapper.local.UserSettingsMapper
import com.example.navigationtest.core.domain.entity.UserSettings
import com.example.navigationtest.core.domain.repository.UserSettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UseSettingsDataRepository(
    private val userSettingsLocalDataSource: UserSettingsLocalDataSource,
) : UserSettingsRepository {
    override fun getUserSettings(): Flow<UserSettings> =
        userSettingsLocalDataSource
            .getUserSettings()
            .map(UserSettingsMapper::toEntity)
            .flowOn(Dispatchers.IO)

    override suspend fun setUserSettings(userSettings: UserSettings) = withContext(Dispatchers.IO) {
        userSettingsLocalDataSource.setUserSettings(
            userSettings.let(UserSettingsMapper::toDataModel),
        )
    }
}
