
var platform = ""
var vmArgs = "-Dempty"
val os = System.getProperty("os.name").toLowerCase()
if (os.contains("linux")) {
    platform = "gtk.linux"
} else if (os.contains("mac")) {
    platform = "cocoa.macosx"
    vmArgs = "-XstartOnFirstThread"
} else if (os.contains("windows")) {
    platform = "win32.win32"
}

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

repositories {
    mavenCentral()
    maven(url = "https://dl.equo.dev/chromium-swt-ce/oss/mvn")
}

dependencies {
    implementation("com.equo:com.equo.chromium.cef.${platform}.x86_64:106.0.0")
    implementation("com.equo:com.equo.chromium:106.0.0")
}

application {
    applicationDefaultJvmArgs = listOf("${vmArgs}")
    mainClass.set("Standalone.StandaloneKt")
}
