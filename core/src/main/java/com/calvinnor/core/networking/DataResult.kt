package com.calvinnor.core.networking

sealed class DataResult<out T> {

    /** Denotes that we successfully found / retrieved some data **/
    data class Success<T>(val data: T) : DataResult<T>()

    /** Denotes that we could not find some data. Throwable in ex **/
    data class Failure<T>(val ex: Throwable) : DataResult<T>()

}
