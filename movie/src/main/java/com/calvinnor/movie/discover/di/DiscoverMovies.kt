package com.calvinnor.movie.discover.di

import com.calvinnor.core.dependencies.base.BaseModule
import com.calvinnor.movie.discover.domain.DiscoverMoviesC
import com.calvinnor.movie.discover.domain.DiscoverMoviesRemote
import com.calvinnor.movie.discover.domain.DiscoverMoviesRepo
import com.calvinnor.movie.discover.viewmodel.DiscoverMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

/**
 * Dependency injection for Discover Movies
 */
object DiscoverMoviesModule : BaseModule {
    override fun load() = loadKoinModules(discoverMoviesModule)
    override fun unload() = unloadKoinModules(discoverMoviesModule)
}

private val discoverMoviesModule = module {

    factory<DiscoverMoviesC.Remote> { DiscoverMoviesRemote(movieWebService = get()) }

    factory<DiscoverMoviesC.Repository> { DiscoverMoviesRepo(remote = get()) }

    viewModel { DiscoverMoviesViewModel(dispatcher = get(), discoverRepo = get()) }

}
