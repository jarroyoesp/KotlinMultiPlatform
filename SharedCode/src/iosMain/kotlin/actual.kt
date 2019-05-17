package com.jarroyo.kotlinmultiplatform

actual fun platformName(): String {

  //return UIDevice.currentDevice.systemName() +
  //        " " +
  //        UIDevice.currentDevice.systemVersion

  return "iOS"
}


//https://stackoverflow.com/a/24505884