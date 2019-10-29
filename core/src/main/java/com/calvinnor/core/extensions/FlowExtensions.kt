package com.calvinnor.core.extensions

import androidx.lifecycle.MutableLiveData
import com.calvinnor.core.dispatchers.Dispatcher
import com.calvinnor.core.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

suspend fun <T> Flow<T>.collectOn(liveData: MutableLiveData<T>, postValue: Boolean = false) {
    if (postValue) collect { liveData.postValue(it) }
    else collect { liveData.value = it }
}

fun <T> Flow<T>.flowOnBack(dispatcher: Dispatcher) = flowOn(dispatcher.io)

suspend fun <T> FlowCollector<Result<T>>.emitSuccess(data: T) = emit(Result.Success(data))

suspend fun <T> FlowCollector<Result<T>>.emitFailure(throwable: Throwable) =
    emit(Result.Failure(throwable))
