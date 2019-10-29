package com.calvinnor.core.dependencies

import com.calvinnor.core.dispatchers.AppDispatcher
import com.calvinnor.core.dispatchers.Dispatcher
import org.koin.dsl.module

val schedulerModule = module {

    /** The application-level dispatcher for scheduling work on threads **/
    single<Dispatcher> { AppDispatcher() }

}
