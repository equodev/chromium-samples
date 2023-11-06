
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
val arch = when {
    System.getProperty("os.arch").toLowerCase().contains("amd64") -> "x86_64"
    else -> System.getProperty("os.arch").toLowerCase()
}


configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.name.contains("org.eclipse.swt.")) {
            useTarget("${requested.group}:org.eclipse.swt.${platform}.${arch}:${requested.version}")
        }
    }
}

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

repositories {
    mavenCentral()
    maven(url = "https://dl.equo.dev/chromium-swt-ee/equo-gpl/mvn")
}

dependencies {
    implementation("com.equo:com.equo.chromium.cef.${platform}.${arch}:106.0.0")
    implementation("com.equo:com.equo.chromium:106.0.0")
    implementation("org.eclipse.platform:org.eclipse.swt.${platform}.${arch}:3.121.0")
    implementation("org.eclipse.platform:org.eclipse.swt:3.121.0")
}

application {
    applicationDefaultJvmArgs = listOf("${vmArgs}")
    mainClass.set("SampleSWT.SampleSWTKt")
}
