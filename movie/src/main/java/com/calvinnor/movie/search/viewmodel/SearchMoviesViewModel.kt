package com.calvinnor.movie.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calvinnor.core.dispatchers.JobDispatcher
import com.calvinnor.core.dispatchers.cancelJobs
import com.calvinnor.core.dispatchers.onIo
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.postLoading
import com.calvinnor.core.extensions.postResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.core.viewmodel.BaseViewModel
import com.calvinnor.movie.commons.model.MovieUiModel
import com.calvinnor.movie.search.di.SearchMoviesModule
import com.calvinnor.movie.search.domain.SearchMoviesC

class SearchMoviesViewModel(
    private val searchRepo: SearchMoviesC.Repository,
    private val jobDispatcher: JobDispatcher

) : BaseViewModel() {

    private val _searchMovies = MutableLiveData<Result<Pagination.Result<MovieUiModel>>>()

    /** Exposed LiveData **/
    val searchMovies: LiveData<Result<Pagination.Result<MovieUiModel>>> = _searchMovies

    fun searchMovies(searchQuery: String) {
        _searchMovies.postLoading(true)
        jobDispatcher.onIo {
            _searchMovies.postResult(
                searchRepo.searchMovies(
                    searchQuery = searchQuery,
                    isNewSearch = true
                )
            )
        }
    }

    fun paginateMovies(searchQuery: String) {
        _searchMovies.postLoading(true)
        jobDispatcher.onIo {
            _searchMovies.postResult(
                searchRepo.searchMovies(
                    searchQuery = searchQuery,
                    isNewSearch = false
                )
            )
        }
    }

    override fun onCleared() {
        SearchMoviesModule.unload()
        jobDispatcher.cancelJobs()
        super.onCleared()
    }
}
