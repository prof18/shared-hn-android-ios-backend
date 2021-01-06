# shared-hn-android-ios-backend
A sample of Android app, iOs app, and backend that share some common code via Kotlin Multiplatform

This is a sample project used in the talk ["And that, folks, is how we shared code  between Android, iOS and the Backend"](https://speakerdeck.com/prof18/and-that-folks-is-how-we-shared-code-between-android-ios-and-the-backend-droidcon-emea) that shows the capabilities of Kotlin Multiplatform.

The branch [kotlin-serialization](https://github.com/prof18/shared-hn-android-ios-backend/tree/kotlin-serialization) uses Kotlin Serialization in all the platforms, like explained in ["Using Retrofit and Alamofire with Kotlin Serialization on Kotlin Multiplatform"](https://www.marcogomiero.com/posts/2020/kotlin-serialization-alamofire/)


## hn-foundation

This is a Kotlin Multiplatform library that it's shared between Android, iOs, and the Backend. For Android and the backend, the library is distributed using a local Maven repository. For iOs instead, the library is distributed using a CocoaPod repository hosted on [GitHub](https://github.com/prof18/hn-foundation-cocoa). 

### Publish artifacts for Android and Backend

```bash
./gradlew publishToMavenLocal
```

### Publish Debug iOs Framework

```bash
./gradlew publishDevFramework
```

### Publish Release iOs Framework
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

## hn-backend

After publishing the artifacts to the local Maven repository you can start the backend on your machine.

## hn-android-client

After publishing the artifacts to the local Maven repository and after starting the backend, you can try the android app. Remember to change the base address of the backend [here](https://github.com/prof18/shared-hn-android-ios-backend/blob/master/hn-android-client/app/src/main/java/com/prof18/hn/android/client/ui/MainViewModel.kt#L23)

## hn-ios-client

You should be able to try the iOs app as is since I've published the Pods. Of course, you can create your CocoaPod repository and try yourself the publishing process. Remember to change the base address of the backend [here](https://github.com/prof18/shared-hn-android-ios-backend/blob/master/hn-ios-client/HN%20Client/HN%20Client/ui/MainViewModel.swift#L20).
