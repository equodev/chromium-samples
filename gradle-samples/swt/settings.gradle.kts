enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "SampleSWT"
include("app")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") { from(files("../../gradle/libs.versions.toml")) }
    }
}
