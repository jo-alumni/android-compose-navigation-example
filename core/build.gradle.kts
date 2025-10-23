plugins {
    id("com.android.library")
    alias(libs.plugins.navigationTest.kotlin)
    alias(libs.plugins.navigationTest.compose)
}

android {
    namespace = "com.example.navigation_test.core"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
