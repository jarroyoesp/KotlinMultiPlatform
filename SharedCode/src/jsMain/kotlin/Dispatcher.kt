package com.jarroyo.sharedcode

import kotlinx.coroutines.Dispatchers.Default
import kotlin.coroutines.CoroutineContext

internal actual val ApplicationDispatcher: CoroutineContext = Default