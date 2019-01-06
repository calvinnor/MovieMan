package com.calvinnor.data.util

import com.calvinnor.data.AppDb
import org.koin.dsl.module.module

fun daoModule(appDb: AppDb) = module {

    single { appDb.movieDao() }

}
