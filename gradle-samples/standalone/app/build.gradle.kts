
val chromiumVersion = "116.0.0"
var vmArgs = "-Dempty"
val os = System.getProperty("os.name").toLowerCase()
val platform = when {
    os.contains("linux") -> "gtk.linux"
    os.contains("win") -> "win32.win32"
    os.contains("mac") -> {
        vmArgs = "-XstartOnFirstThread"
        "cocoa.macosx"
    }
    else -> ""
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
    implementation("com.equo:com.equo.chromium.cef.${platform}.x86_64:${chromiumVersion}")
    implementation("com.equo:com.equo.chromium:${chromiumVersion}")
}

application {
    applicationDefaultJvmArgs = listOf("${vmArgs}")
    mainClass.set("Standalone.StandaloneKt")
}
