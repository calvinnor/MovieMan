package com.calvinnor.movie.discover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calvinnor.core.dispatchers.Dispatcher
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.collectOn
import com.calvinnor.core.extensions.flowOnBack
import com.calvinnor.core.extensions.hasValue
import com.calvinnor.core.extensions.setLoading
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.core.viewmodel.BaseViewModel
import com.calvinnor.movie.commons.model.MovieUiModel
import com.calvinnor.movie.discover.di.DiscoverMoviesModule
import com.calvinnor.movie.discover.domain.DiscoverMoviesC

class DiscoverMoviesViewModel(
    private val discoverRepo: DiscoverMoviesC.Repository,
    private val dispatcher: Dispatcher

) : BaseViewModel(dispatcher) {

    private val _discoverMovies = MutableLiveData<Result<Pagination.Result<MovieUiModel>>>()

    /** Exposed LiveData **/
    val discoverMovies: LiveData<Result<Pagination.Result<MovieUiModel>>> = _discoverMovies

    fun getMovies() {
        if (_discoverMovies.hasValue()) return
        getMoreMovies()
    }

    fun getMoreMovies() {
        _discoverMovies.setLoading(postValue = true)
        launchOnMain {
            discoverRepo.getPopularMovies()
                .flowOnBack(dispatcher)
                .collectOn(_discoverMovies)
        }
    }

    override fun onCleared() {
        DiscoverMoviesModule.unload()
        super.onCleared()
    }
}
