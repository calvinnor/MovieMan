package com.calvinnor.core.networking

import kotlinx.coroutines.Deferred

/**
 * Wrapper utility for an API call.
 *
 * This will wrap around the call, and if successful, return the Success DataResult.
 * Else, will return DataResult.Failure with the exception.
 */
suspend fun <T> callApi(deferredApi: Deferred<T>): DataResult<T> {
    return try {
        DataResult.Success(deferredApi.await())

    } catch (ex: Exception) {
        DataResult.Failure(ex)
    }
}
