package com.calvinnor.movieman

import com.calvinnor.movieman.data.remote.MovieWebService
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.create

val movieModule = module {

    factory { get<Retrofit>().create<MovieWebService>() }

}