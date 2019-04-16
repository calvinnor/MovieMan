package com.calvinnor.core.dependencies

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {

    single { androidContext() }

}
