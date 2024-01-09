
val chromiumVersion = "95.0.29"
val chromiumPlatformVersion = "95.0.21"
val os = System.getProperty("os.name").toLowerCase()
var vmArgs = mutableListOf<String>()
var platform = ""
if (os.contains("linux")) {
    platform = "gtk.linux"
} else if (os.contains("mac")) {
    platform = "cocoa.macosx"
    vmArgs.add("-XstartOnFirstThread")
} else if (os.contains("windows")) {
    platform = "win32.win32"
}
if(JavaVersion.current().majorVersion.toInt() > 16) {
    vmArgs.add("-Dkotlin.daemon.jvm.options=--illegal-access=permit")
}

plugins {
    kotlin("jvm") version "1.5.10"
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
    mainClass.set("Standalone.StandaloneKt")
}
