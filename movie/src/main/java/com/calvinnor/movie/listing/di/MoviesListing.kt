package com.calvinnor.movie.listing.di

import com.calvinnor.core.dependencies.base.BaseModule
import com.calvinnor.movie.listing.domain.MoviesListingC
import com.calvinnor.movie.listing.domain.MoviesListingRemote
import com.calvinnor.movie.listing.domain.MoviesListingRepo
import com.calvinnor.movie.listing.viewmodel.MoviesListingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

/**
 * Dependency injection for Discover Movies
 */
object SectionMoviesModule : BaseModule {

    override fun load() {
        // TODO(calvinnor): This is a hack to prevent re-initialisation of Koin modules when
        // a new Fragment is init. Find a way to check if these already exist
        unloadKoinModules(sectionMoviesModule)
        loadKoinModules(sectionMoviesModule)
    }

    override fun unload() = unloadKoinModules(sectionMoviesModule)
}

private val sectionMoviesModule = module {

    factory<MoviesListingC.Remote> { MoviesListingRemote(movieWebService = get()) }

    factory<MoviesListingC.Repository> { MoviesListingRepo(remote = get()) }

    viewModel { MoviesListingViewModel(dispatcher = get(), sectionRepo = get()) }

}
