package convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import primitive.bundle
import primitive.implementation
import primitive.libs
import primitive.plugin

@Suppress("unused")
class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply(libs.plugin("navigationTest.compose").pluginId)
                apply(libs.plugin("navigationTest.kotlin").pluginId)
                apply(libs.plugin("navigationTest.hilt").pluginId)
            }

            dependencies {
                implementation(libs.bundle("orbit"))
            }
        }
    }
}
