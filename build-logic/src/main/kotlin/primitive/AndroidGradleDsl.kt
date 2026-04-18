package primitive

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

fun Project.androidApplication(action: ApplicationExtension.() -> Unit) {
    extensions.configure<ApplicationExtension>(action)
}

fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure<LibraryExtension>(action)
}

fun Project.android(action: CommonExtension.() -> Unit) {
    val app = extensions.findByType(ApplicationExtension::class.java)
    if (app != null) {
        action(app)
        return
    }

    val library = extensions.findByType(LibraryExtension::class.java)
    if (library != null) {
        action(library)
        return
    }

    error("Android extension is not registered in project: $path")
}

fun Project.kotlinAndroidOptions(configure: KotlinAndroidProjectExtension.() -> Unit) {
    extensions.configure(configure)
}

fun Project.libraryAndroidOptions(configure: LibraryAndroidComponentsExtension.() -> Unit) {
    extensions.configure(configure)
}

fun Project.setupAndroid() {
    androidApplication {
        namespace?.let {
            this.namespace = it
        }

        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildFeatures.buildConfig = true

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
//            isCoreLibraryDesugaringEnabled = true
        }
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
    }
}
