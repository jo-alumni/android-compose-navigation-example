plugins {
    alias(libs.plugins.navigationTest.feature)
}

android {
    namespace = "com.example.navigation_test.app.feature.todo"
    compileSdk = 36
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.app.core)
}
