package com.example.navigationtest.core.local.datastore.mapper

import com.example.navigationtest.core.data.model.UserSettingsModel
import com.example.navigationtest.core.local.datastore.model.UserSettingsDatastoreModel

object UserSettingsMapper {
    fun toDataModel(dataStoreModel: UserSettingsDatastoreModel): UserSettingsModel =
        UserSettingsModel(
            userName = dataStoreModel.userName,
            notificationsEnabled = dataStoreModel.notificationsEnabled,
        )

    fun toDataStoreModel(dataModel: UserSettingsModel): UserSettingsDatastoreModel =
        UserSettingsDatastoreModel(
            userName = dataModel.userName,
            notificationsEnabled = dataModel.notificationsEnabled,
        )
}
