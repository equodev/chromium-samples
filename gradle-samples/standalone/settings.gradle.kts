enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "standalone"
include("app")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") { from(files("../../gradle/libs.versions.toml")) }
    }
}
