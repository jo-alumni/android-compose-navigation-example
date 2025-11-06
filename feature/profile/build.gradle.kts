plugins {
    alias(libs.plugins.navigationTest.feature)
}

android {
    namespace = "com.example.navigation_test.feature.profile"
    compileSdk = 36
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(projects.core)
    implementation(projects.domain)
}
