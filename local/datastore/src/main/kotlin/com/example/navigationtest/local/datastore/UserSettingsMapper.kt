package com.example.navigationtest.local.datastore

import com.example.navigationtest.data.model.UserSettingsModel
import com.example.navigationtest.local.datastore.model.UserSettingsDatastoreModel

object UserSettingsMapper {
    fun toDataModel(dataStoreModel: UserSettingsDatastoreModel): UserSettingsModel = UserSettingsModel(
        userName = dataStoreModel.userName,
        notificationsEnabled = dataStoreModel.notificationsEnabled,
    )
}
