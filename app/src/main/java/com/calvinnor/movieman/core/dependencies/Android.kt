package com.calvinnor.movieman.core.dependencies

import com.calvinnor.movieman.app.Main
import org.koin.dsl.module.module

val androidModule = module {

    single { Main.appContext }

}
