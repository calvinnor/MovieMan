package com.calvinnor.core.networking

sealed class ApiResult<out T> {

    /** Denotes an HTTP success **/
    data class Success<T>(val result: T) : ApiResult<T>()

    /** Denotes an HTTP Failure **/
    data class Failure<T>(val ex: Throwable) : ApiResult<T>()

}
