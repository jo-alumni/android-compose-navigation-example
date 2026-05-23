package com.example.navigationtest.core.data.datarepository

import com.example.navigationtest.core.common.di.IoDispatcher
import com.example.navigationtest.core.data.datasource.UserSettingsLocalDataSource
import com.example.navigationtest.core.data.mapper.local.UserSettingsMapper
import com.example.navigationtest.core.domain.entity.UserSettings
import com.example.navigationtest.core.domain.repository.UserSettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseSettingsDataRepository @Inject constructor(
    private val userSettingsLocalDataSource: UserSettingsLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : UserSettingsRepository {
    override fun getUserSettings(): Flow<UserSettings> =
        userSettingsLocalDataSource
            .getUserSettings()
            .map(UserSettingsMapper::toEntity)
            .flowOn(ioDispatcher)

    override suspend fun setUserSettings(userSettings: UserSettings) = withContext(ioDispatcher) {
        userSettingsLocalDataSource.setUserSettings(
            userSettings.let(UserSettingsMapper::toDataModel),
        )
    }
}
