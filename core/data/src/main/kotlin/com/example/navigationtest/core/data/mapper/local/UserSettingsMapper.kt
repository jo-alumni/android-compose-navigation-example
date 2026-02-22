package com.example.navigationtest.core.data.mapper.local

import com.example.navigationtest.core.data.model.UserSettingsModel
import com.example.navigationtest.core.domain.entity.UserSettings

object UserSettingsMapper {
    fun toDataModel(entity: UserSettings): UserSettingsModel = UserSettingsModel(
        userName = entity.userName,
        notificationsEnabled = entity.notificationsEnabled,
    )

    fun toEntity(dataModel: UserSettingsModel): UserSettings = UserSettings(
        userName = dataModel.userName,
        notificationsEnabled = dataModel.notificationsEnabled,
    )
}
