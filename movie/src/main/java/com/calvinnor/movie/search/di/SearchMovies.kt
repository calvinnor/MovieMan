package com.calvinnor.movie.search.di

import com.calvinnor.core.dependencies.base.BaseModule
import com.calvinnor.movie.search.domain.SearchMoviesC
import com.calvinnor.movie.search.domain.SearchMoviesRemote
import com.calvinnor.movie.search.domain.SearchMoviesRepo
import com.calvinnor.movie.search.viewmodel.SearchMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

/**
 * Dependency injection for Search Movies
 */
object SearchMoviesModule : BaseModule {
    override fun load() = loadKoinModules(searchMoviesModule)
    override fun unload() = unloadKoinModules(searchMoviesModule)
}

val searchMoviesModule = module {

    factory<SearchMoviesC.Remote> { SearchMoviesRemote(movieWebService = get()) }

    factory<SearchMoviesC.Repository> { SearchMoviesRepo(remote = get()) }

    viewModel { SearchMoviesViewModel(jobDispatcher = get(), searchRepo = get()) }

}
