package convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.example.navigation_test.primitive.android.compose")
                apply("com.example.navigation_test.primitive.android.kotlin")
            }
        }
    }
}
