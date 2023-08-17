
val chromiumVersion = "95.0.12"
val chromiumJxVersion = "${chromiumVersion}.1"
var vmArgs = "-Dempty"
var envVars = mutableMapOf<String, String>()
val os = System.getProperty("os.name").toLowerCase()
val platform = when {
    os.contains("linux") -> {
    	envVars["GDK_BACKEND"] = "x11"
        vmArgs = "-Dchromium.init_threads=true"
        "gtk.linux"
    }
    os.contains("win") -> "win32.win32"
    os.contains("mac") -> {
        vmArgs = "-XstartOnFirstThread"
        "cocoa.macosx"
    }
    else -> ""
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
    maven(url = "https://dl.equo.dev/chromium-swt-ee/jx/mvn")
}

dependencies {
    implementation("com.equo:com.equo.chromium.cef.${platform}.x86_64:${chromiumVersion}")
    implementation("com.equo:com.equo.chromium.jx:${chromiumJxVersion}")
    implementation("org.eclipse.platform:org.eclipse.swt.${platform}.x86_64:3.121.0")
    implementation("org.eclipse.platform:org.eclipse.swt:3.121.0")
}

application {
    applicationDefaultJvmArgs = listOf("${vmArgs}")
    mainClass.set("SampleSWT.SampleSWTKt")
    tasks.named<JavaExec>("run") {
        environment(envVars)
    }
}
