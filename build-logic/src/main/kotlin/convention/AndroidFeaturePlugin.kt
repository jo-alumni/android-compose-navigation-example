package convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import primitive.libs

@Suppress("unused")
class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("navigationTest.app").get().get().pluginId)
                apply(libs.findPlugin("navigationTest.compose").get().get().pluginId)
                apply(libs.findPlugin("navigationTest.kotlin").get().get().pluginId)
            }
        }
    }
}
