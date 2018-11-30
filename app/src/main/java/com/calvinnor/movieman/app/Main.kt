package com.calvinnor.movieman.app

import android.app.Application
import android.content.Context
import com.calvinnor.movieman.core.dependencies.androidModule
import com.calvinnor.movieman.core.dependencies.networkModule
import com.calvinnor.movieman.core.dependencies.storageModule
import com.calvinnor.movieman.movieModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin

/**
 * Android's Application class.
 * Use for 3rd party library init and other setup.
 */
class Main : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        initialiseStetho()
        initialiseKoin()
    }

    private fun initialiseStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initialiseKoin() {
        startKoin(
            this, modules = listOf(
                androidModule, networkModule, storageModule,

                // TODO: Try to separate out into a feature module
                movieModule
            )
        )
    }

    companion object {
        lateinit var appContext: Context
    }
}
