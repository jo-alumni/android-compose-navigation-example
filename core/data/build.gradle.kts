import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    alias(libs.plugins.navigationTest.kotlin)
    alias(libs.plugins.navigationTest.hilt)
}

android {
    namespace = "com.example.navigation_test.core.data"

    compileSdk = 36

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.add("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode")
    }
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.common)
}
