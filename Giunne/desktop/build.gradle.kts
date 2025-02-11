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
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Giunne"
            packageVersion = "1.0.0"
        }
        jvmArgs += listOf(
            "-Xmx2G"
        )
    }
}

javafx {
    version = "22"
    modules("javafx.base", "javafx.media", "javafx.swing", "javafx.controls", "javafx.graphics")
}

//buildscript {
//    dependencies {
//        classpath (libs.multiplatform.resources.generator)
//    }
//}