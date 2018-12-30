package com.calvinnor.movie.discover.di

import com.calvinnor.movie.discover.domain.DiscoverMoviesC
import com.calvinnor.movie.discover.domain.DiscoverMoviesRemote
import com.calvinnor.movie.discover.domain.DiscoverMoviesRepo
import com.calvinnor.movie.discover.viewmodel.DiscoverMoviesViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val discoverMoviesModule = module {

    factory<DiscoverMoviesC.Remote> { DiscoverMoviesRemote(movieWebService = get()) }

    factory<DiscoverMoviesC.Repository> { DiscoverMoviesRepo(remote = get()) }

    viewModel { DiscoverMoviesViewModel(jobDispatcher = get(), discoverRepo = get()) }

}
