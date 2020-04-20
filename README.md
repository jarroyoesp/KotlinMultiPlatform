# Kotlin-Multiplatform (Android, iOS, JS & JVM Desktop App)
![kotlin-version](https://img.shields.io/badge/kotlin-1.3.50-orange)
<a target="_blank" href="https://androidweekly.net/issues/issue-397"><img src="https://androidweekly.net/issues/issue-397/badge"></a>
<a target="_blank" href="https://androidweekly.net/issues/issue-380"><img src="https://androidweekly.net/issues/issue-380/badge"></a>
![kotlin-weekly](https://img.shields.io/endpoint?label=Kotlin-Weekly%20%23165&style=plastic&url=https%3A%2F%2Fmailchi.mp%2Fkotlinweekly%2Fkotlin-weekly-165)

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

This example shows how to create a simple Android/iOS/JVM/JS project sharing some Kotlin code. This app saves on a local database your favourites locations and get the current weather of them from OpenWeatherMap.

In this app we share common code for both platforms (Android/iOS) to get data from an API (in this case OpenWeatherMap). The libraries that we use are:

- [KTOR](https://github.com/ktorio/ktor): to make HTTP requests
- [Serialization](https://github.com/Kotlin/kotlinx.serialization): to De/Serializing JSON 
- [SqlDelight](https://github.com/square/sqldelight): Local database shared between Android & iOS 

Tests:

- [MockK](https://mockk.io/#gradlemaven-dependency): mocking library for Kotlin

<img src="https://github.com/jarroyoesp/KotlinMultiPlatform/blob/master/images/GetLocationDiagram.png">

### Android App - Master Branch

With this app you can get the weather of your locations using KTor:

<img src="https://github.com/jarroyoesp/KotlinMultiPlatform/blob/master/images/AndroidCaptureWeather.png" width="200">

And add and save on SQLDelight database your favourite locations:

<img src="https://github.com/jarroyoesp/KotlinMultiPlatform/blob/master/images/AndroidCaptureLocationList.png" width="200">

### iOS App

Open XCode and select the project:

<img src="https://github.com/jarroyoesp/KotlinMultiPlatform/blob/master/images/iOS_App.png" width="200">

### JVM - Desktop App

To execute this version as desktop app, you have to execute this code on your console:

```
./gradlew JavaFxApp:run
```
By default the JDBC SQLite is created in memory. If you want to persist your database in your disk, you have to indicate the path when you create the database driver:

```
SQLDriver.kt
JdbcSqliteDriver("jdbc:sqlite:$your_database_path")
```

<img src="https://github.com/jarroyoesp/KotlinMultiPlatform/blob/master/images/JVMApp.png" width="800">

### JS App 

If you want to launch a web to get the weather you can try executing the next commands:

```
./gradlew JsApp:run
./gradlew BackEnd:run
--> localhost:8080
```

Current weather is not shown when you launch the browser? If you have this problem (Module not found: Error: Can't resolve 'core-js/features/object/assign') try to reinstall the node module core-js:
```
npm install core-js
```

<img src="https://github.com/jarroyoesp/KotlinMultiPlatform/blob/master/images/jsApp.png" width="400">

I have followed these examples:

https://kotlinlang.org/docs/tutorials/native/mpp-ios-android.html

https://www.raywenderlich.com/1022411-kotlin-multiplatform-project-for-android-and-ios-getting-started

https://proandroiddev.com/kotlin-multiplatform-very-beginners-guide-part-3-database-e34c92daf41c




