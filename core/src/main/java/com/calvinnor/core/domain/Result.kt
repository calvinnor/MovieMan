package com.calvinnor.core.domain

sealed class Result<out T> {

    /** Denotes that we are loading something **/
    data class Loading<T>(val isLoading: Boolean = true) : Result<T>()

    /** Denotes that we have data **/
    data class Success<T>(val data: T) : Result<T>()

    /** Denotes a failure to fetch the data **/
    data class Failure<T>(val ex: Throwable) : Result<T>()

}
