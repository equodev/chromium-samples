
val chromiumVersion = "128.0.8"
val chromiumPlatformVersion = "128.0.8"
val os = System.getProperty("os.name").toLowerCase()
var vmArgs = mutableListOf<String>()
val platform = when {
    os.contains("linux") -> "gtk.linux"
    os.contains("win") -> {
        "win32.win32"
    }
    os.contains("mac") -> { 
        vmArgs.addAll(listOf("--add-opens", "java.desktop/java.awt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.awt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.lwawt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.lwawt.macosx=ALL-UNNAMED"))
        "cocoa.macosx"
    }
    else -> ""
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
    implementation("com.equo:com.equo.chromium:${chromiumVersion}")
}

application {
    applicationDefaultJvmArgs = vmArgs
    mainClass.set("Swing.SwingKt")
}
