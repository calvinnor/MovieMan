package com.calvinnor.data.util

import com.calvinnor.data.movie.remote.MovieWebService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val webServiceModule = module {

    single { get<Retrofit>().create<MovieWebService>() }

}
