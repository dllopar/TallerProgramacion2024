plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.6.10"
    id("app.cash.sqldelight") version "2.0.1"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {

        val ktorVersion = "2.0.0"
        val napierVersion = "2.6.1"

        commonMain {
            dependencies {
                // KTOR
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                // NAPIER
                implementation("io.github.aakira:napier:$napierVersion")

                // SERIALIZATION
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
                //SQLDligth
                implementation(libs.sqlite.driver)
            }
        }

        androidMain {
            dependencies {
                // KTOR
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

                implementation(libs.android.driver)
            }
        }

        iosMain {
            dependencies {
                // KTOR
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.example.pokedex")
        }
    }
}

android {
    namespace = "com.example.tp_pokemon_2024"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.firebase.crashlytics.buildtools)
}
///
