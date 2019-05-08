package com.jarroyo.kotlinmultiplatform

import kotlinx.coroutines.*
import platform.Foundation.NSData
import platform.UIKit.UIImage
import platform.UIKit.UIView
import platform.darwin.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.freeze

internal actual val ApplicationDispatcher: CoroutineDispatcher =
    NsQueueDispatcher(dispatch_get_main_queue())

internal class NsQueueDispatcher(
    private val dispatchQueue: dispatch_queue_t
) : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }
}