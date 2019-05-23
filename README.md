# Kotlin-Multiplatform

This example shows how to create a simple Android/iOS project sharing some Kotlin code.

In this app we share common code for both platforms (Android/iOS) to get data from an API (in this case OpenWeatherAPI). The libraries that we use are:

- KTOR: to make HTTP requests [https://github.com/ktorio/ktor]
- Serialization: to De/Serializing JSON [https://github.com/Kotlin/kotlinx.serialization]
- SqlDelight: Local database shared between Android & iOS [https://github.com/square/sqldelight]

<img src="https://github.com/jarroyoesp/KotlinMultiPlatform/blob/master/images/GetLocationDiagram.png">

With this app you can get the weather of your locations using KTor:

<img src="https://github.com/jarroyoesp/KotlinMultiPlatform/blob/master/images/AndroidCaptureWeather.png">

And add and save on SQLDelight database your favourite locations:

<img src="https://github.com/jarroyoesp/KotlinMultiPlatform/blob/master/images/AndroidCaptureLocationList.png">


I have followed these examples:

https://kotlinlang.org/docs/tutorials/native/mpp-ios-android.html

https://www.raywenderlich.com/1022411-kotlin-multiplatform-project-for-android-and-ios-getting-started

https://proandroiddev.com/kotlin-multiplatform-very-beginners-guide-part-3-database-e34c92daf41c


