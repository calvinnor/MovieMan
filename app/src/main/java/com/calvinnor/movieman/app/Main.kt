package com.calvinnor.movieman.app

import com.calvinnor.core.application.CoreApp
import com.calvinnor.data.AppDb
import com.calvinnor.data.util.daoModule
import com.calvinnor.data.util.webServiceModule

/**
 * Android's Application class.
 * Use for 3rd party library init and other setup.
 */
class Main : CoreApp() {

    override fun getDataModules() = arrayOf(
        daoModule(AppDb.getDatabase(applicationContext)),
        webServiceModule
    )
}
