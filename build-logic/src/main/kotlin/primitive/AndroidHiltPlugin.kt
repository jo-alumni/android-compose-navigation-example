package primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("hilt").pluginId)
                apply(libs.plugin("ksp").pluginId)
            }
            dependencies {
                implementation(libs.library("hilt-android"))
                implementation(libs.library("hilt-compose"))
                ksp(libs.library("hilt-compiler"))
            }
        }
    }
}
