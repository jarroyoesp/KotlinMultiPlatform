package com.jarroyo.sharedcode.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<T>(private val coroutineContext: CoroutineContext) {

    protected var view: T? = null
    protected lateinit var scope: PresenterCoroutineScope

    fun attachView(view: T) {
        this.view = view
        scope = PresenterCoroutineScope(coroutineContext)
        onViewAttached(view)
    }

    protected open fun onViewAttached(view: T) {}

    fun detachView() {
        view = null
        scope.viewDetached()
        onViewDetached()
    }

    protected open fun onViewDetached() {}
}

class PresenterCoroutineScope(
    context: CoroutineContext
) : CoroutineScope {

    private var onViewDetachJob = Job()
    override val coroutineContext: CoroutineContext = context + onViewDetachJob

    fun viewDetached() {
        onViewDetachJob.cancel()
    }
}
