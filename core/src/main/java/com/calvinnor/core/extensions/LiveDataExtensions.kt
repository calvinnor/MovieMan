package com.calvinnor.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.calvinnor.core.domain.Result

/**
 * Check if the LiveData does not hold a value.
 */
fun LiveData<*>.isEmpty() = value == null

/**
 * Check if the LiveData holds a value.
 */
fun LiveData<*>.hasValue() = !isEmpty()

/**
 * Checks if the Result LiveData is not idle
 * i.e. it is loading something, or contains a result
 */
fun <T> LiveData<Result<T>>.isNotIdle() =
    hasValue() && (value is Result.Loading || value is Result.Success)

fun <T> LiveData<Result<T>>.isLoading() = hasValue() && value is Result.Loading

/**
 * Set a Loading State on this LiveData.
 *
 * @param isLoading The Result Loading boolean
 * @param postValue Whether the value should be set (same thread) or posted
 */
fun <T> MutableLiveData<Result<T>>.setLoading(
    isLoading: Boolean = true,
    postValue: Boolean = false
) {
    if (postValue) postValue(Result.Loading(isLoading))
    else value = Result.Loading(isLoading)
}

/**
 * Wrapper over the LiveData observe for using Lambdas.
 */
inline fun <T> LifecycleOwner.observe(
    liveData: LiveData<T>,
    crossinline codeBlock: (data: T) -> Unit

) = liveData.observe(this, Observer { codeBlock(it) })
