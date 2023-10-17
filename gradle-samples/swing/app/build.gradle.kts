
val chromiumVersion = "116.0.0"
val os = System.getProperty("os.name").toLowerCase()
var vmArgs = mutableListOf<String>()
if(os.contains("mac") && JavaVersion.current().majorVersion.toInt() <= 16) {
    vmArgs.addAll(listOf("--add-opens", "java.desktop/java.awt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.awt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.lwawt=ALL-UNNAMED", "--add-opens", "java.desktop/sun.lwawt.macosx=ALL-UNNAMED"))
}
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
    maven(url = "https://dl.equo.dev/chromium-swt-ce/oss/mvn")
}

dependencies {
    implementation("com.equo:com.equo.chromium.cef.${platform}.x86_64:${chromiumVersion}")
    implementation("com.equo:com.equo.chromium:${chromiumVersion}")
}

application {
    applicationDefaultJvmArgs = vmArgs
    mainClass.set("Swing.SwingKt")
}
