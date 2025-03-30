import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.squareup.javapoet.FieldSpec
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
}

group = "com.project.giunne"
version = "1.0-SNAPSHOT"

val versionProps = Properties()
versionProps.load(FileInputStream(rootDir.path + "/gradle.properties"))

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.koin.android.compose)
}

android {
    namespace = "com.project.giunne"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.project.giunne.android"
        minSdk = 24
        targetSdk = 34
        versionCode = versionProps["appVersionCode"].toString().toInt()
        versionName = versionProps["appVersionName"].toString()

        multiDexEnabled = true
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildTypes {
        getByName("release") {
            //noinspection GradlePath
            applicationIdSuffix = ".release"
            isMinifyEnabled = true
            multiDexEnabled = true
            proguardFiles("proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
}

tasks.register("BuildAndRun") {
    doFirst {
        exec {
            workingDir(projectDir.parentFile)
            commandLine("./gradlew", "android:build")
            commandLine("./gradlew", "android:installDebug")
        }
    }
}