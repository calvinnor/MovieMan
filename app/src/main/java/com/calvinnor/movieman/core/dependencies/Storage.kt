package com.calvinnor.movieman.core.dependencies

import android.preference.PreferenceManager
import org.koin.dsl.module.module

val storageModule = module {

    single { PreferenceManager.getDefaultSharedPreferences(get()) }

}
