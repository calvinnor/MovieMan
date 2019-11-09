package com.calvinnor.movie.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calvinnor.core.dispatchers.Dispatcher
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.collectOn
import com.calvinnor.core.extensions.flowOnBack
import com.calvinnor.core.extensions.isLoading
import com.calvinnor.core.extensions.setLoading
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.core.viewmodel.BaseViewModel
import com.calvinnor.movie.commons.model.MovieUiModel
import com.calvinnor.movie.search.di.SearchMoviesModule
import com.calvinnor.movie.search.domain.SearchMoviesC

class SearchMoviesViewModel(
    private val searchRepo: SearchMoviesC.Repository,
    private val dispatcher: Dispatcher

) : BaseViewModel(dispatcher) {

    private val _searchMovies = MutableLiveData<Result<Pagination.Result<MovieUiModel>>>()

    /** Exposed LiveData **/
    val searchMovies: LiveData<Result<Pagination.Result<MovieUiModel>>> = _searchMovies

    fun searchMovies(searchQuery: String) {
        if (_searchMovies.isLoading()) return

        _searchMovies.setLoading()
        searchMoviesIntl(searchQuery, isNewSearch = true)
    }

    fun paginateMovies(searchQuery: String) {
        if (_searchMovies.isLoading()) return

        _searchMovies.setLoading(postValue = true)
        searchMoviesIntl(searchQuery, isNewSearch = false)
    }

    private fun searchMoviesIntl(searchQuery: String, isNewSearch: Boolean) =
        launchOnMain {
            searchRepo.searchMovies(searchQuery, isNewSearch)
                .flowOnBack(dispatcher)
                .collectOn(_searchMovies)
        }

    override fun onCleared() {
        SearchMoviesModule.unload()
        super.onCleared()
    }
}
