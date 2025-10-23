plugins {
    id("com.android.library")
    alias(libs.plugins.navigationTest.kotlin)
}

android {
    namespace = "com.example.navigation_test.core.domain"

    compileSdk = 36
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
