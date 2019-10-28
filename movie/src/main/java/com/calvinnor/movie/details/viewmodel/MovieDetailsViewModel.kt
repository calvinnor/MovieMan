package com.calvinnor.movie.details.viewmodel

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
import com.calvinnor.movie.details.di.MovieDetailsModule
import com.calvinnor.movie.details.domain.MovieDetailsC
import com.calvinnor.movie.details.model.MovieDetailsUiModel

class MovieDetailsViewModel(
    private val movieRepo: MovieDetailsC.Repository,
    private val jobDispatcher: JobDispatcher

) : BaseViewModel() {

    private val _movieDetails = MutableLiveData<Result<MovieDetailsUiModel>>()

    /** Exposed LiveData **/
    val movieDetails: LiveData<Result<MovieDetailsUiModel>> = _movieDetails

    fun getMovieDetails(movieId: String) {
        if (_movieDetails.hasValue()) return

        _movieDetails.postLoading(true)
        jobDispatcher.onIo {
            _movieDetails.postResult(movieRepo.getMovieDetails(movieId = movieId))
        }
    }

    override fun onCleared() {
        MovieDetailsModule.unload()
        jobDispatcher.cancelJobs()
        super.onCleared()
    }
}
