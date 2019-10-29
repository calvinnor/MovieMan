package com.calvinnor.core.extensions

import com.calvinnor.core.exceptions.NoDataException
import com.calvinnor.core.networking.DataResult

/**
 * Convert a Nullable object into a Result.
 *
 * Result.Success with the data if found
 * Else a Result.Failure with the provided exception.
 *
 * @param exception The exception to throw if null.
 */
fun <T> T?.toDataResult(exception: Throwable = NoDataException()): DataResult<T> = let {
    if (it == null) DataResult.Failure(exception)
    else DataResult.Success(it)
}
