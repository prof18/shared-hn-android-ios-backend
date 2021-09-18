import java.util.*
import java.text.SimpleDateFormat
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("com.android.library")
    kotlin("multiplatform") version "1.5.30"
    id("maven-publish")
    kotlin("plugin.serialization") version "1.5.30"
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

val libName = "HNFoundation"
val libGroup = "com.prof18.hn.foundation"
val libVersionName = "2.0.0"
val libVersionCode = 20000

group = libGroup
version = libVersionName

// To publish on a online maven repo
//publishing {
//    repositories {
//        maven {
//            credentials {
//                username = "username"
//                password = "pwd"
//            }
//            url = url("https://mymavenrepo.it")
//        }
//    }
//}

android {
    compileSdkVersion(30)
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = libVersionCode
        versionName = libVersionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    val xcFramework = XCFramework(libName)

    jvm()

    android {
        publishAllLibraryVariants()
        publishLibraryVariantsGroupedByFlavor = true
    }

    ios {
        binaries.framework(libName) {
            xcFramework.add(this)
        }
    }

    version = libVersionName

    sourceSets {

        all {
            languageSettings.apply {
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation("co.touchlab:stately-common:1.1.9")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.6.0")
            }
        }
        val androidTest by getting
        val iosMain by getting
        val iosTest by getting
        val jvmMain by getting
        val jvmTest by getting
    }

    tasks {

        named<Copy>("jvmProcessResources") {
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        }

        register("publishDevFramework") {
            description = "Publish iOs framework to the Cocoa Repo"

            doFirst {
                project.exec {
                    workingDir = File("$rootDir/../../hn-foundation-cocoa-xcframework")
                    commandLine("git", "checkout", "develop").standardOutput
                }
            }

            dependsOn("assemble${libName}DebugXCFramework")

            doLast {

                copy {
                    from("$buildDir/XCFrameworks/debug")
                    into("$rootDir/../../hn-foundation-cocoa-xcframework")
                }

                val dir = File("$rootDir/../../hn-foundation-cocoa-xcframework/$libName.podspec")
                val tempFile = File("$rootDir/../../hn-foundation-cocoa-xcframework/$libName.podspec.new")

                val reader = dir.bufferedReader()
                val writer = tempFile.bufferedWriter()
                var currentLine: String?

                while (reader.readLine().also { currLine -> currentLine = currLine } != null) {
                    if (currentLine?.startsWith("s.version") == true) {
                        writer.write("s.version       = \"${libVersionName}\"" + System.lineSeparator())
                    } else {
                        writer.write(currentLine + System.lineSeparator())
                    }
                }
                writer.close()
                reader.close()
                val successful = tempFile.renameTo(dir)

                if (successful) {

                    project.exec {
                        workingDir = File("$rootDir/../../hn-foundation-cocoa-xcframework")
                        commandLine(
                            "git",
                            "add",
                            "."
                        ).standardOutput
                    }

                    val dateFormatter = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
                    project.exec {
                        workingDir = File("$rootDir/../../hn-foundation-cocoa-xcframework")
                        commandLine(
                            "git",
                            "commit",
                            "-m",
                            "\"New dev release: ${libVersionName}-${dateFormatter.format(Date())}\""
                        ).standardOutput
                    }

                    project.exec {
                        workingDir = File("$rootDir/../../hn-foundation-cocoa-xcframework")
                        commandLine("git", "push", "origin", "develop").standardOutput
                    }
                }
            }
        }

        register("publishFramework") {
            description = "Publish iOs framework to the Cocoa Repo"

            doFirst {
                project.exec {
                    workingDir = File("$rootDir/../../hn-foundation-cocoa-xcframework")
                    commandLine("git", "checkout", "main").standardOutput
                }
            }

            // Create Release Framework for Xcode
            dependsOn("assemble${libName}ReleaseXCFramework")

            // Replace
            doLast {

                copy {
                    from("$buildDir/XCFrameworks/release")
                    into("$rootDir/../../hn-foundation-cocoa-xcframework")
                }

                val dir = File("$rootDir/../../hn-foundation-cocoa-xcframework/$libName.podspec")
                val tempFile = File("$rootDir/../../hn-foundation-cocoa-xcframework/$libName.podspec.new")

                val reader = dir.bufferedReader()
                val writer = tempFile.bufferedWriter()
                var currentLine: String?

                while (reader.readLine().also { currLine -> currentLine = currLine } != null) {
                    if (currentLine?.startsWith("s.version") == true) {
                        writer.write("s.version       = \"${libVersionName}\"" + System.lineSeparator())
                    } else {
                        writer.write(currentLine + System.lineSeparator())
                    }
                }
                writer.close()
                reader.close()
                val successful = tempFile.renameTo(dir)

                if (successful) {

                    project.exec {
                        workingDir = File("$rootDir/../../hn-foundation-cocoa-xcframework")
                        commandLine(
                            "git",
                            "add",
                            "."
                        ).standardOutput
                    }

                    project.exec {
                        workingDir = File("$rootDir/../../hn-foundation-cocoa-xcframework")
                        commandLine("git", "commit", "-m", "\"New release: ${libVersionName}\"").standardOutput
                    }

                    project.exec {
                        workingDir = File("$rootDir/../../hn-foundation-cocoa-xcframework")
                        commandLine("git", "tag", libVersionName).standardOutput
                    }

                    project.exec {
                        workingDir = File("$rootDir/../../hn-foundation-cocoa-xcframework")
                        commandLine("git", "push", "origin", "main", "--tags").standardOutput
                    }
                }
            }
        }
    }
}
