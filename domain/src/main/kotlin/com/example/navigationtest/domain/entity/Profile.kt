package com.example.navigationtest.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: String,
    val name: String,
    val description: String,
) {
    companion object {
        fun fake(suffix: String? = null) =
            if (suffix == null) {
                Profile(
                    id = "1",
                    name = "John Doe",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                )
            } else {
                Profile(
                    id = "id_$suffix",
                    name = "John Doe $suffix",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. $suffix",
                )
            }
    }
}
