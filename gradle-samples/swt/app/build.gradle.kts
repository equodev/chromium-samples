
var platform = ""
var vmArgs = "-Dempty"
val os = System.getProperty("os.name").toLowerCase()
if (os.contains("linux")) {
    platform = "gtk.linux"
    vmArgs = "-Dchromium.init_threads=true"
} else if (os.contains("mac")) {
    platform = "cocoa.macosx"
    vmArgs = "-XstartOnFirstThread"
} else if (os.contains("windows")) {
    platform = "win32.win32"
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.name.contains("org.eclipse.swt.")) {
            useTarget("${requested.group}:org.eclipse.swt.${platform}.x86_64:${requested.version}")
        }
    }
}

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

repositories {
    mavenCentral()
    maven(url = "https://dl.equo.dev/chromium-swt-ce/trial/mvn")
}

dependencies {
    implementation("com.equo:com.equo.chromium.cef.${platform}.x86_64:106.0.0")
    implementation("com.equo:com.equo.chromium:106.0.0")
    implementation("org.eclipse.platform:org.eclipse.swt.${platform}.x86_64:3.121.0")
    implementation("org.eclipse.platform:org.eclipse.swt:3.121.0")
}

application {
    applicationDefaultJvmArgs = listOf("${vmArgs}")
    mainClass.set("SampleSWT.SampleSWTKt")
}
