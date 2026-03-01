import org.gradle.api.Project
import java.util.Properties

fun Project.getEnv(key: String, default: String? = null): String {
    val localProperties = Properties().apply {
        rootProject.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
    }
    return localProperties.getProperty(key) ?: System.getenv(key) ?: default
    ?: throw IllegalArgumentException("required property '$key' not found and no default value provided")
}

