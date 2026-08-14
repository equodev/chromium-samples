enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "swing"
include("app")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") { from(files("../../gradle/libs.versions.toml")) }
    }
}
