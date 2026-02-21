package com.example.navigationtest.data.mapper.local

import com.example.navigationtest.data.model.UserSettingsModel
import com.example.navigationtest.domain.entity.UserSettings

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
