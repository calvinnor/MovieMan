package com.calvinnor.core.dependencies

import com.calvinnor.core.dispatchers.AppDispatcher
import com.calvinnor.core.dispatchers.Dispatcher
import com.calvinnor.core.dispatchers.JobDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val schedulerModule = module {

    /** The application-level dispatcher for scheduling work on threads **/
    single<Dispatcher> { AppDispatcher() }

    /** A Job for performing work. Exceptions in child Jobs do not cancel all Jobs **/
    factory<Job> { SupervisorJob() }

    /** A CoroutineScope with a Parent Job. A new scope is created per screen **/
    factory { CoroutineScope(context = get<Job>()) }

    /** CoroutineScope + Dispatcher. Allows us to abstract the logic of dispatching behind methods **/
    factory { JobDispatcher(dispatcher = get(), parentScope = get()) }

}
