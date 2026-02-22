package com.example.navigationtest.core.local.datastore.model

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlinx.serialization.json.Json.Default.encodeToString
import kotlinx.serialization.serializer
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class UserSettingsDatastoreModel(
    val userName: String = "",
    val notificationsEnabled: Boolean = true,
)

object UserSettingsSerializer : Serializer<UserSettingsDatastoreModel> {
    override val defaultValue: UserSettingsDatastoreModel
        get() = UserSettingsDatastoreModel()

    override suspend fun readFrom(input: InputStream): UserSettingsDatastoreModel {
        return decodeFromString(
            deserializer = serializer(),
            string = input.bufferedReader().use { it.readText() },
        )
    }

    override suspend fun writeTo(t: UserSettingsDatastoreModel, output: OutputStream) {
        val jsonString = encodeToString(
            serializer = serializer(),
            value = t,
        )
        withContext(Dispatchers.IO) {
            output.write(jsonString.toByteArray())
        }
    }
}
