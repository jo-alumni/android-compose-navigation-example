package primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            android {
                buildFeatures.compose = true
            }
            composeCompiler {
                enableStrongSkippingMode.set(true)
            }
            dependencies {
                implementation(platform(libs.library("androidx-compose-bom")))
                implementation(libs.library("androidx-core-ktx"))
                implementation(libs.library("androidx-ui"))
                implementation(libs.library("androidx-material3"))
                implementation(libs.library("androidx-material-icon"))
                implementation(libs.library("androidx-ui-tooling-preview"))
                implementation(libs.library("androidx-navigation-compose"))
                implementation(libs.library("coil-compose"))
                implementation(libs.library("coil-okhttp"))
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("androidx-espresso-core"))
                testImplementation(libs.library("androidx-ui-test-junit4"))
                debugImplementation(libs.library("androidx-ui-tooling"))
                debugImplementation(libs.library("androidx-ui-test-manifest"))
            }
        }
    }
}
