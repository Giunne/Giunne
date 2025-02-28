import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec

group = "com.project.giunne"
version = "1.0-SNAPSHOT"

fun getMappingValue(key: String): String {
    return gradleLocalProperties(rootDir).getProperty(key)
}

plugins {
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqlDelght)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.buildkonfig)
}

sqldelight {
    databases {
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.foundation)
                api(compose.material3)
                api(compose.runtime)
                api(compose.ui)
                api(compose.components.resources)

                api(libs.androidx.lifecycle.viewmodel)
                api(libs.androidx.lifecycle.runtime.compose)
                api(libs.androidx.collection)

                api(libs.koin.core)
                api(libs.napier)
                api(libs.kotlinx.serialization.json)

                api(libs.decompose)
                api(libs.decompose.extention)

                api(libs.bundles.reaktive)

                api(libs.multiplatform.settings)

                api(libs.sqlDelight.runtime)
                api(libs.sqlDelight.adapter)

                api(libs.coil)
                api(libs.coil.compose)
                api(libs.coil.core)
                api(libs.coil.network.ktor2)

//                implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.3")
//                api(libs.coil.core)
//                api(libs.coil.compose)
//                api(libs.coil.compose.core)
//                api(libs.coil.network.ktor)
                api(libs.compottie)
                api(libs.ktorfit)
                api(libs.bundles.ktor)

                api(libs.androidx.graphics.shapes)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.activity.compose)
                api(libs.androidx.appcompat)
                api(libs.androidx.core)
                api(libs.koin.core)
                api(libs.koin.android)
                api(libs.sqlDelight.android)
                api(libs.ktor.engine.android)
                api(libs.permissions)
                api(libs.androidx.media3.exoplayer)
                api(libs.androidx.media3.exoplayer.dash)
                api(libs.androidx.media3.ui)
//                api(libs.multiplatform.resources.generator)
//                api(libs.coil.android)
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.desktop.common)
                api(compose.preview)
                api(libs.skiko.macos)
                api(libs.ktor.okHttp)
                api(libs.koin.core)
                api(libs.koin.jvm)
                api(libs.reaktive.utils)
                api(libs.sqlDelight.jvm)
                api(libs.jdbc)
                api(libs.kotlinx.coroutines.swing)
                api(libs.ktor.engine.jvm)

                compileOnly("org.openjfx:javafx-base:22:mac")
                compileOnly("org.openjfx:javafx-swing:22:mac")
                compileOnly("org.openjfx:javafx-media:22:mac")
                compileOnly("org.openjfx:javafx-controls:22:mac")
                compileOnly("org.openjfx:javafx-graphics:22:mac")
            }
        }
    }
}

android {
    namespace = "com.project.giunne"
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 34

        multiDexEnabled = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildToolsVersion = "34.0.0"
}
dependencies {
    implementation(libs.androidx.foundation.android)
}

compose.resources {
    publicResClass = true
    generateResClass = auto
    packageOfResClass = "com.project.giunne"
}

buildkonfig {
    packageName = "com.project.giunne"

    // default config is required
    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "BASE_URL", getMappingValue("BASE_URL"), const = true)
    }
}