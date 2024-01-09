
val chromiumVersion = "116.0.1"
val chromiumPlatformVersion = "116.0.1"
val os = System.getProperty("os.name").toLowerCase()
var vmArgs = mutableListOf<String>()
var platform = ""
if (os.contains("linux")) {
    platform = "gtk.linux"
} else if (os.contains("mac")) {
    platform = "cocoa.macosx"
    vmArgs.addAll(listOf("--add-opens", "java.desktop/java.awt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.awt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.lwawt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.lwawt.macosx=ALL-UNNAMED"))
} else if (os.contains("windows")) {
    platform = "win32.win32"
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
    implementation("com.equo:com.equo.chromium.cef.${platform}.x86_64:${chromiumPlatformVersion}")
    implementation("com.equo:com.equo.chromium:${chromiumVersion}")
}

application {
    applicationDefaultJvmArgs = vmArgs
    mainClass.set("Swing.SwingKt")
}
