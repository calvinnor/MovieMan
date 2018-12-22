package com.calvinnor.core.networking

import kotlinx.coroutines.Deferred

/**
 * Wrapper utility for an API call.
 *
 * This will wrap around the call, and if successful, return the Success ApiResult.
 * Else, will return ApiResult.Failure with the exception.
 */
suspend fun <T> callApi(deferredApi: Deferred<T>): ApiResult<T> {
    return try {
        ApiResult.Success(deferredApi.await())

    } catch (ex: Exception) {
        ApiResult.Failure(ex)
    }
}
