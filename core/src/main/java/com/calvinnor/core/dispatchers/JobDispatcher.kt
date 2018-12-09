package com.calvinnor.core.dispatchers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * A wrapper for Coroutines Jobs + Dispatchers.
 * We can achieve some abstractions and concise code rather than passing barebones instances.
 */
class JobDispatcher(val dispatcher: Dispatcher, val parentScope: CoroutineScope)

/** Cancel all Jobs launched with this Context **/
fun JobDispatcher.cancelJobs() = parentScope.coroutineContext.cancel()

/** Execute a piece of code on the Main Thread with the provided scope **/
fun JobDispatcher.onMain(lambda: suspend () -> Unit) =
    parentScope.launch(dispatcher.main) { lambda() }

/** Execute a lambda on an IO Thread with the provided scope **/
fun JobDispatcher.onIo(lambda: suspend () -> Unit) =
    parentScope.launch(dispatcher.io) { lambda() }
