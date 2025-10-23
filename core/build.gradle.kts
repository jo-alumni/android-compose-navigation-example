plugins {
    id("com.android.library")
    alias(libs.plugins.navigationTest.kotlin)
    alias(libs.plugins.navigationTest.compose)
}

android {
    namespace = "com.example.navigation_test.core"

    compileSdk = 36
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
