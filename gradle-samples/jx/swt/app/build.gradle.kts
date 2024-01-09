
val chromiumVersion = "106.0.22"
val chromiumPlatformVersion = "106.0.20"
val chromiumJxVersion = "106.0.0.0"
var vmArgs = mutableListOf<String>()
var envVars = mutableMapOf<String, String>()
val os = System.getProperty("os.name").toLowerCase()
var platform = ""
if (os.contains("linux")) {
    envVars["GDK_BACKEND"] = "x11"
    platform = "gtk.linux"
    vmArgs.add("-Dchromium.init_threads=true")
} else if (os.contains("mac")) {
    platform = "cocoa.macosx"
    vmArgs.add("-XstartOnFirstThread")
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
    kotlin("jvm") version "1.8.20"
    application
}

repositories {
    mavenCentral()
    maven(url = "https://dl.equo.dev/chromium-swt-ee/equoSamples/mvn")
}

dependencies {
    implementation("com.equo:com.equo.chromium.cef.${platform}.x86_64:${chromiumPlatformVersion}")
    implementation("com.equo:com.equo.chromium.jx:${chromiumJxVersion}")
    implementation("org.eclipse.platform:org.eclipse.swt.${platform}.x86_64:3.121.0")
    implementation("org.eclipse.platform:org.eclipse.swt:3.121.0")
}

application {
    applicationDefaultJvmArgs = vmArgs
    mainClass.set("SampleSWT.SampleSWTKt")
    tasks.named<JavaExec>("run") {
        environment(envVars)
    }
}
