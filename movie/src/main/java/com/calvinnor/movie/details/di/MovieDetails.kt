package com.calvinnor.movie.details.di

import com.calvinnor.core.dependencies.base.BaseModule
import com.calvinnor.movie.details.domain.MovieDetailsC
import com.calvinnor.movie.details.domain.MovieDetailsLocal
import com.calvinnor.movie.details.domain.MovieDetailsRemote
import com.calvinnor.movie.details.domain.MovieDetailsRepo
import com.calvinnor.movie.details.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

/**
 * Dependency Injection module for Movie Details
 */
object MovieDetailsModule : BaseModule {
    override fun load() = loadKoinModules(movieDetailsModule)
    override fun unload() = unloadKoinModules(movieDetailsModule)
}

private val movieDetailsModule = module {

    factory<MovieDetailsC.Local> { MovieDetailsLocal(movieDao = get()) }

    factory<MovieDetailsC.Remote> { MovieDetailsRemote(movieWebService = get()) }

    factory<MovieDetailsC.Repository> { MovieDetailsRepo(local = get(), remote = get()) }

    viewModel { MovieDetailsViewModel(jobDispatcher = get(), movieRepo = get()) }

}
