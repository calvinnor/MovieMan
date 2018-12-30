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
import com.calvinnor.core.viewmodel.BaseViewModel
import com.calvinnor.movie.discover.domain.DiscoverMoviesC
import com.calvinnor.movie.discover.model.MovieUiModel

class DiscoverMoviesViewModel(
    private val discoverRepo: DiscoverMoviesC.Repository,
    private val jobDispatcher: JobDispatcher

) : BaseViewModel() {

    private val _discoverMovies = MutableLiveData<Result<List<MovieUiModel>>>()

    /** Exposed LiveData **/
    val discoverMovies: LiveData<Result<List<MovieUiModel>>> = _discoverMovies

    fun getMovieDetails() {
        if (_discoverMovies.hasValue()) return

        _discoverMovies.postLoading(true)
        jobDispatcher.onIo {
            _discoverMovies.postResult(discoverRepo.getPopularMovies())
        }
    }

    override fun onCleared() {
        jobDispatcher.cancelJobs()
        super.onCleared()
    }
}
