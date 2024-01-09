
val chromiumVersion = "95.0.29"
val chromiumPlatformVersion = "95.0.21"
val os = System.getProperty("os.name").toLowerCase()
var vmArgs = mutableListOf<String>()
var platform = ""
if (os.contains("linux")) {
    platform = "gtk.linux"
} else if (os.contains("mac")) {
    platform = "cocoa.macosx"
    if(JavaVersion.current().majorVersion.toInt() <= 16) {
        vmArgs.addAll(listOf("--add-opens", "java.desktop/java.awt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.awt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.lwawt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.lwawt.macosx=ALL-UNNAMED"))
    }
} else if (os.contains("windows")) {
    platform = "win32.win32"
}
if(JavaVersion.current().majorVersion.toInt() > 16) {
   vmArgs.add("-Dkotlin.daemon.jvm.options=--illegal-access=permit")
}
vmArgs.add("-Dempty")

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
    mainClass.set("Swing.SwingKt")
}
