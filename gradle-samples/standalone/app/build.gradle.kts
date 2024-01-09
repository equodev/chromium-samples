
var platform = ""
var vmArgs = mutableListOf<String>()
val chromiumVersion = "116.0.1"
val chromiumPlatformVersion = "116.0.1"
val os = System.getProperty("os.name").toLowerCase()
if (os.contains("linux")) {
    platform = "gtk.linux"
} else if (os.contains("mac")) {
    platform = "cocoa.macosx"
    vmArgs.add("-XstartOnFirstThread")
} else if (os.contains("windows")) {
    platform = "win32.win32"
}

val arch = when {
    System.getProperty("os.arch").toLowerCase().contains("amd64") -> "x86_64"
    else -> System.getProperty("os.arch").toLowerCase()
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
}

application {
    applicationDefaultJvmArgs = vmArgs
    mainClass.set("Standalone.StandaloneKt")
}
