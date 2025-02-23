package primitive

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.android(action: TestedExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.kotlinAndroidOptions(configure: KotlinAndroidProjectExtension.() -> Unit) {
    extensions.configure(configure)
}

fun Project.libraryAndroidOptions(configure: LibraryAndroidComponentsExtension.() -> Unit) {
    extensions.configure(configure)
}

fun Project.setupAndroid() {
    android {
        namespace?.let {
            this.namespace = it
        }
        compileSdkVersion(35)

        defaultConfig {
            minSdk = 24
            targetSdk = 35
        }

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
