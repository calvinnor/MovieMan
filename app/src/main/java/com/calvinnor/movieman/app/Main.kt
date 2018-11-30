package com.calvinnor.movieman.app

import com.calvinnor.core.application.CoreApp
import com.calvinnor.movieman.movieModule

/**
 * Android's Application class.
 * Use for 3rd party library init and other setup.
 */
class Main : CoreApp() {

    override fun getFeatureModules() = arrayOf(movieModule)
}
