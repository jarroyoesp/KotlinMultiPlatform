package com.jarroyo.kotlinmultiplatform

expect fun platformName(): String

fun createApplicationScreenMessage(): String {
  return "Kotlin Rocks on ${platformName()}"
}
