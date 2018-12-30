package com.calvinnor.movie.commons

import com.calvinnor.movie.commons.data.remote.MovieWebService
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.create

val movieModule = module {

    factory { get<Retrofit>().create<MovieWebService>() }

}