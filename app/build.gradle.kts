import java.util.Properties

plugins {
    alias(libs.plugins.navigationTest.app)
    alias(libs.plugins.navigationTest.compose)
    alias(libs.plugins.navigationTest.kotlin)
    alias(libs.plugins.navigationTest.hilt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ksp)
}

val localProperties = Properties().apply {
    rootProject.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}

fun Properties.getEnv(key: String, default: String? = null): String =
    getProperty(key) ?: System.getenv(key) ?: default ?: throw IllegalArgumentException("Property '$key' not found in local.properties or environment variables")


android {
    namespace = "com.example.navigation_test"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.navigation_test"
        minSdk = 31
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_BASE_URL", "\"${localProperties.getEnv("API_BASE_URL")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // core modules
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.api)
    implementation(projects.core.local.datastore)
    implementation(projects.core.local.database)

    // app modules
    implementation(projects.app.core)
    implementation(projects.app.feature.posts)
    implementation(projects.app.feature.postDetail)
    implementation(projects.app.feature.todo)
}
