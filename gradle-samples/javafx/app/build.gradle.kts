
var platform = ""
var vmArgs = mutableListOf<String>()
val chromiumVersion = extra["chromiumVersion"] as String
val chromiumPlatformVersion = extra["chromiumPlatformVersion"] as String
val javafxVersion = "21.0.5"
val os = System.getProperty("os.name").toLowerCase()
if (os.contains("linux")) {
    platform = "gtk.linux"
} else if (os.contains("mac")) {
    platform = "cocoa.macosx"
} else if (os.contains("windows")) {
    platform = "win32.win32"
}
val arch = when {
    System.getProperty("os.arch").toLowerCase().contains("amd64") -> "x86_64"
    else -> System.getProperty("os.arch").toLowerCase()
}
// OpenJFX artifacts are classified per OS/arch (unlike the single com.equo.chromium artifact).
val javafxClassifier = when {
    os.contains("mac") && arch == "aarch64" -> "mac-aarch64"
    os.contains("mac") -> "mac"
    os.contains("windows") -> "win"
    else -> "linux"
}

plugins {
    kotlin("jvm") version "1.8.20"
    application
}

repositories {
    mavenCentral()
    maven(url = "https://dl.equo.dev/chromium-swt-ee/equoSamples/mvn")
}

dependencies {
    implementation("com.equo:com.equo.chromium.cef.${platform}.${arch}:${chromiumPlatformVersion}")
    implementation("com.equo:com.equo.chromium:${chromiumVersion}")
    // The browser is embedded as a JavaFX Node.
    for (module in listOf("base", "graphics", "controls")) {
        implementation("org.openjfx:javafx-$module:$javafxVersion:$javafxClassifier")
    }
}

application {
    applicationDefaultJvmArgs = vmArgs
    // Non-Application entry point (top-level main) so the classpath launch works without --module-path.
    mainClass.set("SampleJavaFx.SampleJavaFxKt")
}
