package com.jarroyo.firstkotlinmultiplatform

import kotlinx.coroutines.*

internal actual val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Default