package com.calvinnor.movie.commons

import com.calvinnor.movie.commons.data.remote.MovieWebService
import com.calvinnor.movie.details.domain.MovieDetailsC
import com.calvinnor.movie.details.domain.MovieDetailsRemote
import com.calvinnor.movie.details.domain.MovieDetailsRepo
import com.calvinnor.movie.details.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.create

val movieModule = module {

    factory { get<Retrofit>().create<MovieWebService>() }

    factory<MovieDetailsC.Remote> { MovieDetailsRemote(movieWebService = get()) }

    factory<MovieDetailsC.Repository> { MovieDetailsRepo(remote = get()) }

    viewModel { MovieDetailsViewModel(jobDispatcher = get(), movieRepo = get()) }

}
