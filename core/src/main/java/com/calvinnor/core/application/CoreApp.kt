package com.calvinnor.core.application

import android.app.Application
import com.calvinnor.core.BuildConfig
import com.calvinnor.core.dependencies.androidModule
import com.calvinnor.core.dependencies.networkModule
import com.calvinnor.core.dependencies.schedulerModule
import com.calvinnor.core.dependencies.storageModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

/**
 * Android's Application class.
 * Used for 3rd party library init and other setup.
 */
abstract class CoreApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initialiseStetho()
        initialiseKoin()
    }

    private fun initialiseStetho() {
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }

    private fun initialiseKoin() {
        startKoin {

            // Logger for Android
            androidLogger(Level.NONE)

            // Set the Android Context
            androidContext(this@CoreApp)

            modules(listOf(*defaultModules(), *getDataModules()))
        }
    }

    private fun defaultModules(): Array<Module> =
        arrayOf(androidModule, networkModule, storageModule, schedulerModule)

    /**
     * Return the modules for Data (Dao, WebServices)
     */
    abstract fun getDataModules(): Array<Module>

}
