
val chromiumVersion = "95.0.12"
val chromiumJxVersion = "${chromiumVersion}.1"
var vmArgs = "-Dempty"
val os = System.getProperty("os.name").toLowerCase()
val platform = when {
    os.contains("linux") -> "gtk.linux"
    os.contains("win") -> "win32.win32"
    os.contains("mac") -> "cocoa.macosx"
    else -> ""
}

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

repositories {
    mavenCentral()
    maven(url = "https://dl.equo.dev/chromium-swt-ee/jx/mvn")
}

dependencies {
    implementation("com.equo:com.equo.chromium.cef.${platform}.x86_64:${chromiumVersion}")
    implementation("com.equo:com.equo.chromium.jx:${chromiumJxVersion}")
}

application {
    applicationDefaultJvmArgs = listOf("${vmArgs}")
    mainClass.set("Swing.SwingKt")
}
