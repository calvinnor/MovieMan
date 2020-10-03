package com.calvinnor.movie.listing.viewmodel

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
import com.calvinnor.movie.commons.model.MoviesSection
import com.calvinnor.movie.commons.model.MovieUiModel
import com.calvinnor.movie.listing.di.SectionMoviesModule
import com.calvinnor.movie.listing.domain.MoviesListingC

class MoviesListingViewModel(
    private val sectionRepo: MoviesListingC.Repository,
    private val dispatcher: Dispatcher

) : BaseViewModel(dispatcher) {

    private val _discoverMovies = MutableLiveData<Result<Pagination.Result<MovieUiModel>>>()

    /** Exposed LiveData **/
    val discoverMovies: LiveData<Result<Pagination.Result<MovieUiModel>>> = _discoverMovies

    fun getMovies(section: MoviesSection) {
        if (_discoverMovies.hasValue()) return
        getMoreMovies(section)
    }

    fun getMoreMovies(section: MoviesSection) {
        _discoverMovies.setLoading(postValue = true)
        launchOnMain {
            sectionRepo.getMovies(section)
                .flowOnBack(dispatcher)
                .collectOn(_discoverMovies)
        }
    }

    override fun onCleared() {
        SectionMoviesModule.unload()
        super.onCleared()
    }
}
