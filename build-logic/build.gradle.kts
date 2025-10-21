import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories {
    google {
        content {
            includeGroupByRegex("com\\.android.*")
            includeGroupByRegex("com\\.google.*")
            includeGroupByRegex("androidx.*")
        }
    }
    mavenCentral()
    gradlePluginPortal()
}

// We are using JDK 17 for build process but we are targeting JDK 11 for the app
// If we use jvmToolchain, we need to install JDK 11
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "17"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.bundles.plugins)
}

gradlePlugin {
    this.plugins {
        // primitive plugins
        register("android-application") {
            id = libs.plugins.navigationTest.app.get().pluginId
            implementationClass = "primitive.AndroidApplicationPlugin"
        }
        register("android-compose") {
            id = libs.plugins.navigationTest.compose.get().pluginId
            implementationClass = "primitive.AndroidComposePlugin"
        }
        register("android-kotlin") {
            id = libs.plugins.navigationTest.kotlin.get().pluginId
            implementationClass = "primitive.AndroidKotlinPlugin"
        }

        // convention plugins
        register("android-feature") {
            id = libs.plugins.navigationTest.feature.get().pluginId
            implementationClass = "convention.AndroidFeaturePlugin"
        }
    }
}
