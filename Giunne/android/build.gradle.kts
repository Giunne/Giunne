plugins {
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
}

group = "com.project.giunne"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.koin.android.compose)
    implementation(platform(libs.firebase.bom))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
}

android {
    namespace = "com.project.giunne"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.project.giunne.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0-SNAPSHOT"

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
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.3"
//    }
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

//buildscript {
//    dependencies {
//        classpath (libs.multiplatform.resources.generator)
//    }
//}