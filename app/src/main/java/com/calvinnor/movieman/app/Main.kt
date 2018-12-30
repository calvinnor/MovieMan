package com.calvinnor.movieman.app

import com.calvinnor.core.application.CoreApp
import com.calvinnor.movie.commons.movieModule
import com.calvinnor.movie.details.di.movieDetailsModule
import com.calvinnor.movie.discover.di.discoverMoviesModule

/**
 * Android's Application class.
 * Use for 3rd party library init and other setup.
 */
class Main : CoreApp() {

    override fun getFeatureModules() =
        arrayOf(movieModule, movieDetailsModule, discoverMoviesModule)
}
