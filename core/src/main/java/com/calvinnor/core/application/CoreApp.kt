package com.calvinnor.core.application

import android.app.Application
import com.calvinnor.core.BuildConfig
import com.calvinnor.core.dependencies.androidModule
import com.calvinnor.core.dependencies.networkModule
import com.calvinnor.core.dependencies.schedulerModule
import com.calvinnor.core.dependencies.storageModule
import com.facebook.stetho.Stetho
import org.koin.core.context.startKoin
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
            modules(
                androidModule(this@CoreApp), networkModule, storageModule, schedulerModule,
                *getDataModules(),
                *getFeatureModules()
            )
        }
    }

    /**
     * Return the modules for Data (Dao, WebServices)
     */
    abstract fun getDataModules(): Array<Module>

    /**
     * Return a list of feature modules for Koin to pick up.
     */
    abstract fun getFeatureModules(): Array<Module>

}
