# Shared code between Android, iOS and Backend
A sample of Android app, iOs app, and backend that share some common code via Kotlin Multiplatform.

There are two branches:

- [main](https://github.com/prof18/shared-hn-android-ios-backend/tree/main): the iOS code is shared using an [XCFramework](https://developer.apple.com/videos/play/wwdc2019/416/)
- [with-ios-fatframework](https://github.com/prof18/shared-hn-android-ios-backend/tree/with-ios-fatframework): the iOS code is shared using a FatFramework.

This sample project is used as reference during my talks, to show how to introduce Kotlin Multiplatform into an existing project:

- ["And that, folks, is how we shared code  between Android, iOS and the Backend - droidcon EMEA"](https://speakerdeck.com/prof18/and-that-folks-is-how-we-shared-code-between-android-ios-and-the-backend-droidcon-emea) -> [with-ios-fatframework](https://github.com/prof18/shared-hn-android-ios-backend/tree/with-ios-fatframework) branch
- ["And that, folks, is how we shared code  between Android, iOS and the Backend - FOSDEM"](https://www.marcogomiero.com/talks/2021/shared-code-kmp-fosdem/) -> [with-ios-fatframework](https://github.com/prof18/shared-hn-android-ios-backend/tree/with-ios-fatframework) branch
- ["Introducing Kotlin Multiplatform in an existing project"](https://www.marcogomiero.com/talks/2021/kmp-existing-project-droidcon-berlin.md/) -> main branch


## Repo Structure

### hn-foundation

This folder contains the Kotlin Multiplatform library that it's shared between Android, iOs, and the Backend. For Android and the backend, the library is distributed using a local Maven repository. For iOs instead, the library is distributed using two CocoaPod repositories hosted on GitHub:

- [hn-foundation-cocoa](https://github.com/prof18/hn-foundation-cocoa): contains the library code in a FatFramework
- [hn-foundation-cocoa-xcframework](https://github.com/prof18/hn-foundation-cocoa-xcframework): contains the library code in an XCFramework

#### Publish artifacts for Android and Backend

```bas
./gradlew publishToMavenLocal
```

#### Publish Debug iOS Framework

```bash
./gradlew publishDevFramework
```

#### Publish Release iOS Framework
```bash
./gradlew publishFramework
```

N.B. if you want to publish to a different CocoaPod repo, you must have a folder organized like that (where the hn-foundation-cocoa is the repo that contains the framework):

```bash
.
├── hn-foundation-cocoa
└── shared-hn-android-ios-backend
    ├── hn-android-client
    ├── hn-backend
    ├── hn-foundation
    └── hn-ios-client
```

If you change the location, remember to customize the path declared in the task on the build.gradle.kts file

### hn-backend

The folder contains a backend written with [Ktor](https://ktor.io/). After publishing the artifacts to the local Maven repository you can start the backend on your machine.

### hn-android-client

Thet folder contains the Android client. After publishing the artifacts to the local Maven repository and after starting the backend, you can try the android app. Remember to change the base address of the backend [here](https://github.com/prof18/shared-hn-android-ios-backend/blob/master/hn-android-client/app/src/main/java/com/prof18/hn/android/client/ui/MainViewModel.kt#L23)

### hn-ios-client

The project contains the iOS client. You should be able to try the iOs app as is since I've published the Pods. Of course, you can create your CocoaPod repository and try yourself the publishing process. Remember to change the base address of the backend [here](https://github.com/prof18/shared-hn-android-ios-backend/blob/master/hn-ios-client/HN%20Client/HN%20Client/ui/MainViewModel.swift#L20).
