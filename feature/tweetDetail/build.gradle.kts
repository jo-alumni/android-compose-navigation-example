plugins {
    alias(libs.plugins.navigationTest.feature)
}

android {
    namespace = "com.example.navigation_test.feature.tweetDetail"
    compileSdk = 36
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
