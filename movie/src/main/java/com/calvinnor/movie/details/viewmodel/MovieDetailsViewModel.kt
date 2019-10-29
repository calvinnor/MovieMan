package com.calvinnor.movie.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calvinnor.core.dispatchers.Dispatcher
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.collectOn
import com.calvinnor.core.extensions.flowOnBack
import com.calvinnor.core.extensions.isNotIdle
import com.calvinnor.core.extensions.setLoading
import com.calvinnor.core.viewmodel.BaseViewModel
import com.calvinnor.movie.details.di.MovieDetailsModule
import com.calvinnor.movie.details.domain.MovieDetailsC
import com.calvinnor.movie.details.model.MovieDetailsUiModel

class MovieDetailsViewModel(
    private val movieRepo: MovieDetailsC.Repository,
    private val dispatcher: Dispatcher

) : BaseViewModel(dispatcher) {

    private val _movieDetails = MutableLiveData<Result<MovieDetailsUiModel>>()

    /** Exposed LiveData **/
    val movieDetails: LiveData<Result<MovieDetailsUiModel>> = _movieDetails

    fun getMovieDetails(movieId: String) {
        if (movieDetails.isNotIdle()) return

        _movieDetails.setLoading()
        launchOnMain {
            movieRepo.getMovieDetails(movieId = movieId)
                .flowOnBack(dispatcher)
                .collectOn(_movieDetails)
        }
    }

    override fun onCleared() {
        MovieDetailsModule.unload()
        super.onCleared()
    }
}
