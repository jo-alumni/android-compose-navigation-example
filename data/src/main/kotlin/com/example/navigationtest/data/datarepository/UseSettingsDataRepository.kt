package com.example.navigationtest.data.datarepository

import com.example.navigationtest.data.datasource.UserSettingsLocalDataSource
import com.example.navigationtest.data.mapper.local.UserSettingsMapper
import com.example.navigationtest.domain.entity.UserSettings
import com.example.navigationtest.domain.repository.UserSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UseSettingsDataRepository(
    private val userSettingsLocalDataSource: UserSettingsLocalDataSource,
) : UserSettingsRepository {
    override fun getUserSettings(): Flow<UserSettings> =
        userSettingsLocalDataSource
            .getUserSettings()
            .map(UserSettingsMapper::toEntity)
}
