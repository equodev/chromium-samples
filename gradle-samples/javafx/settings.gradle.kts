enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "javafx"
include("app")

val tomlContent = file("../../gradle/libs.versions.toml").readText()
val chromiumVersion = Regex("""^chromium\s*=\s*"([^"]+)"""", RegexOption.MULTILINE).find(tomlContent)!!.groupValues[1]
val chromiumPlatformVersion = Regex("""^chromium-platform\s*=\s*"([^"]+)"""", RegexOption.MULTILINE).find(tomlContent)!!.groupValues[1]

gradle.beforeProject {
    extra["chromiumVersion"] = chromiumVersion
    extra["chromiumPlatformVersion"] = chromiumPlatformVersion
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") { from(files("../../gradle/libs.versions.toml")) }
    }
}
