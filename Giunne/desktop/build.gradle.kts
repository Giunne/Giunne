import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

group = "com.project.giunne"
version = "1.0-SNAPSHOT"

plugins {
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.javafx)
}

fun getMappingValue(key: String): String {
    return gradleLocalProperties(rootDir).getProperty(key)
}

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(libs.compose.ui.util)
                implementation(compose.desktop.currentOs)
                implementation(libs.skiko.macos)
                implementation(libs.skiko.window)
                implementation(libs.slf4j)

                api("org.openjfx:javafx-base:22")
                api("org.openjfx:javafx-swing:22")
                api("org.openjfx:javafx-media:22")
                api("org.openjfx:javafx-controls:22")
                api("org.openjfx:javafx-graphics:22")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            modules("java.sql", "java.instrument", "java.compiler", "jdk.unsupported")
            targetFormats(TargetFormat.Exe)
            copyright = "Giunne all rights reserved."
            vendor = "Giunne"
            packageName = "Giunne"
            packageVersion = "1.0.3"
            includeAllModules = true
            description = "Giunne Project"
            windows {
                iconFile.set(file(project.file("giunne_icon.ico")))
                shortcut = true
                menu = true
                menuGroup = "Giunne"
            }
            buildTypes.release.proguard {
                configurationFiles.from(project.file("proguard-rules.pro"))
                isEnabled.set(false)
                obfuscate.set(false)
            }
        }
        jvmArgs += listOf(
            "-Xmx2G",
//            "-Dfile.encoding=UTF-8"
        )
    }
}

javafx {
    version = "22"
    modules("javafx.base", "javafx.media", "javafx.swing", "javafx.controls", "javafx.graphics")
}