plugins {
    id("com.android.library")
    alias(libs.plugins.navigationTest.kotlin)
    alias(libs.plugins.navigationTest.hilt)
}

android {
    namespace = "com.example.navigation_test.data"

    compileSdk = 36

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(projects.domain)
    implementation(libs.kotlinx.serialization.json)
}
