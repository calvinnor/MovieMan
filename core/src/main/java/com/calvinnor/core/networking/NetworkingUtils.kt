package com.calvinnor.core.networking

/**
 * Wrapper utility for an API call.
 *
 * This will wrap around the call, and if successful, return the Success DataResult.
 * Else, will return DataResult.Failure with the exception.
 */
suspend inline fun <T> callApi(crossinline apiCall: suspend () -> T): DataResult<T> {
    return try {
        DataResult.Success(apiCall())

    } catch (ex: Exception) {
        DataResult.Failure(ex)
    }
}
