package com.calvinnor.movie.discover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calvinnor.core.dispatchers.JobDispatcher
import com.calvinnor.core.dispatchers.cancelJobs
import com.calvinnor.core.dispatchers.onIo
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.hasValue
import com.calvinnor.core.extensions.postLoading
import com.calvinnor.core.extensions.postResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.core.viewmodel.BaseViewModel
import com.calvinnor.movie.discover.di.DiscoverMoviesModule
import com.calvinnor.movie.discover.domain.DiscoverMoviesC
import com.calvinnor.movie.discover.model.MovieUiModel

class DiscoverMoviesViewModel(
    private val discoverRepo: DiscoverMoviesC.Repository,
    private val jobDispatcher: JobDispatcher

) : BaseViewModel() {

    private val _discoverMovies = MutableLiveData<Result<Pagination.Result<MovieUiModel>>>()

    /** Exposed LiveData **/
    val discoverMovies: LiveData<Result<Pagination.Result<MovieUiModel>>> = _discoverMovies

    fun getMovies() {
        if (_discoverMovies.hasValue()) return
        getMoreMovies()
    }

    fun getMoreMovies(offset: Long = 0L) {
        _discoverMovies.postLoading(true)
        jobDispatcher.onIo {
            _discoverMovies.postResult(discoverRepo.getPopularMovies(offset = offset))
        }
    }

    override fun onCleared() {
        DiscoverMoviesModule.unload()
        jobDispatcher.cancelJobs()
        super.onCleared()
    }
}
