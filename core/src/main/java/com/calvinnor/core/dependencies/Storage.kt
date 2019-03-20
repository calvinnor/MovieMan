package com.calvinnor.core.dependencies

import android.preference.PreferenceManager
import org.koin.dsl.module

val storageModule = module {

    single { PreferenceManager.getDefaultSharedPreferences(get()) }

}
