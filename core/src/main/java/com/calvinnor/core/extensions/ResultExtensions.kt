package com.calvinnor.core.extensions

import com.calvinnor.core.domain.Result
import com.calvinnor.core.exceptions.NoDataException

/**
 * Convert a Nullable object into a Result.
 *
 * Result.Success with the data if found
 * Else a Result.Failure with the provided exception.
 *
 * @param exception The exception to throw if null.
 */
fun <T> T?.toResult(exception: Throwable = NoDataException()): Result<T> = let {
    if (it == null) Result.Failure(exception)
    else Result.Success(it)
}
