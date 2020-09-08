import java.util.*
import java.text.SimpleDateFormat

plugins {
    kotlin("multiplatform") version "1.4.0"
    id("com.android.library")
    id("kotlin-android-extensions")
    id("maven-publish")
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

val libName = "HN-DTO"
val libGroup = "com.prof18.hn.dto"
val libVersionName = "1.0.0-SNAPSHOT"
val libVersionCode = 10000

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
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = libVersionCode
        versionName = libVersionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

kotlin {
    android()
    jvm()
    ios {
        binaries.framework(libName)
    }

    version = libVersionName


    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.2.0")
            }
        }
        val androidTest by getting
        val iosMain by getting
        val iosTest by getting
        val jvmMain by getting
        val jvmTest by getting
    }

    tasks {
        register("universalFrameworkDebug", org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask::class) {
            baseName = libName
            from(
                iosArm64().binaries.getFramework(libName, "Debug"),
                iosX64().binaries.getFramework(libName, "Debug")
            )
            destinationDir = buildDir.resolve("bin/universal/debug")
            group = libName
            description = "Create the debug framework for iOs"
            dependsOn("linkDebugFrameworkIosArm64")
            dependsOn("linkDebugFrameworkIosX64")
        }

        register("universalFrameworkRelease", org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask::class) {
            baseName = libName
            from(
                iosArm64().binaries.getFramework(libName, "Release"),
                iosX64().binaries.getFramework(libName, "Release")
            )
            destinationDir = buildDir.resolve("$rootDir/../../hn-dto-cocoa")
            group = libName
            description = "Create the release framework for iOs"
            dependsOn("linkReleaseFrameworkIosArm64")
            dependsOn("linkReleaseFrameworkIosX64")
        }

        register("universalFramework") {
            description = "Create the debug and release framework for iOs"
            dependsOn("universalFrameworkDebug")
            dependsOn("universalFrameworkRelease")
        }

        register("publishDevFramework") {
            description = "Publish iOs framweork to the Cocoa Repo"

            // Create Release Framework for Xcode
            dependsOn("universalFrameworkRelease")

            // Replace
            doLast {
                val dir = File("$rootDir/../../hn-dto-cocoa/HN-DTO.podspec")
                val tempFile = File("$rootDir/../../hn-dto-cocoa/HN-DTO.podspec.new")

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
                        workingDir = File("$rootDir/../../hn-dto-cocoa")
                        commandLine(
                            "git",
                            "checkout",
                            "develop"
                        ).standardOutput
                    }

                    val dateFormatter = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
                    project.exec {
                        workingDir = File("$rootDir/../../hn-dto-cocoa")
                        commandLine(
                            "git",
                            "commit",
                            "-a",
                            "-m",
                            "\"New dev release: ${libVersionName}-${dateFormatter.format(Date())}\""
                        ).standardOutput
                    }

                    project.exec {
                        workingDir = File("$rootDir/../../hn-dto-cocoa")
                        commandLine("git", "push", "origin", "develop").standardOutput
                    }
                }
            }
        }

        register("publishFramework") {
            description = "Publish iOs framework to the Cocoa Repo"

            // Create Release Framework for Xcode
            dependsOn("universalFrameworkRelease")

            // Replace
            doLast {
                val dir = File("$rootDir/../../hn-dto-cocoa/HN-DTO.podspec")
                val tempFile = File("$rootDir/../../hn-dto-cocoa/HN-DTO.podspec.new")

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
                        workingDir = File("$rootDir/../../hn-dto-cocoa")
                        commandLine(
                            "git",
                            "checkout",
                            "master"
                        ).standardOutput
                    }

                    project.exec {
                        workingDir = File("$rootDir/../../hn-dto-cocoa")
                        commandLine("git", "commit", "-a", "-m", "\"New release: ${libVersionName}\"").standardOutput
                    }

                    project.exec {
                        workingDir = File("$rootDir/../../hn-dto-cocoa")
                        commandLine("git", "tag", libVersionName).standardOutput
                    }

                    project.exec {
                        workingDir = File("$rootDir/../../hn-dto-cocoa")
                        commandLine("git", "push", "origin", "master", "--tags").standardOutput
                    }
                }
            }
        }

        register("publishAll") {
            description = "Publish JVM and Android artifacts to Nexus and push iOs framweork to the Cocoa Repo"
            // Publish JVM and Android artifacts to Nexus
            dependsOn("publish")
            // Create Release Framework for Xcode
            dependsOn("universalFrameworkRelease")

            // Replace
            doLast {
                val dir = File("$rootDir/../../hn-dto-cocoa/HN-DTO.podspec")
                val tempFile = File("$rootDir/../../hn-dto-cocoa/HN-DTO.podspec.new")

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
                        workingDir = File("$rootDir/../../hn-dto-cocoa")
                        commandLine("git", "commit", "-a", "-m", "\"New release: ${libVersionName}\"").standardOutput
                    }

                    project.exec {
                        workingDir = File("$rootDir/../../hn-dto-cocoa")
                        commandLine("git", "tag", libVersionName).standardOutput
                    }

                    project.exec {
                        workingDir = File("$rootDir/../../hn-dto-cocoa")
                        commandLine("git", "push", "origin", "master", "--tags").standardOutput
                    }
                }
            }
        }
    }

    android {
        publishAllLibraryVariants()
        publishLibraryVariantsGroupedByFlavor = true
    }
}
