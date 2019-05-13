# Kotlin-Multiplatform

This example shows how to create a simple Android/iOS project sharing some Kotlin code.

In this app we share common code for both platforms (Android/iOS) to get data from an API (in this case OpenWeatherAPI). The libraries that we use are:

- KTOR: to make HTTP requests [https://github.com/ktorio/ktor]
- Serialization: to De/Serializing JSON [https://github.com/Kotlin/kotlinx.serialization]
- SqlDelight: Local database shared between Android & iOS [https://github.com/square/sqldelight]

<img src="https://github.com/jarroyoesp/KotlinMultiPlatform/blob/master/images/GetLocationDiagram.png" width="200">

I have followed these examples:

https://kotlinlang.org/docs/tutorials/native/mpp-ios-android.html

https://www.raywenderlich.com/1022411-kotlin-multiplatform-project-for-android-and-ios-getting-started
