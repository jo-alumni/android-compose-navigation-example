plugins {
    id("com.android.library")
    alias(libs.plugins.navigationTest.kotlin)
    alias(libs.plugins.navigationTest.hilt)
}

android {
    namespace = "com.example.navigation_test.local.database"

    compileSdk = 36

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(projects.data)
    implementation(projects.core)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}
