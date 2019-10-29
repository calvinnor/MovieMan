package com.calvinnor.movie.search.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.emitFailure
import com.calvinnor.core.extensions.emitSuccess
import com.calvinnor.core.networking.DataResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.movie.commons.model.MovieUiModel
import kotlinx.coroutines.flow.flow

class SearchMoviesRepo(
    private val remote: SearchMoviesC.Remote

) : SearchMoviesC.Repository {

    private var currentPage: Int = DEFAULT_PAGE
    private var dataItems: MutableList<MovieUiModel> = mutableListOf()

    override suspend fun searchMovies(searchQuery: String, isNewSearch: Boolean) =
        flow<Result<Pagination.Result<MovieUiModel>>> {
            if (isNewSearch) {
                currentPage = DEFAULT_PAGE
                dataItems.clear()
            }

            when (val apiResult =
                remote.searchMovies(searchQuery = searchQuery, requestPage = currentPage + 1)) {

                is DataResult.Success -> {
                    val movieUiModels = apiResult.data.results.map { MovieUiModel(it) }

                    currentPage = apiResult.data.page
                    dataItems.addAll(movieUiModels)

                    emitSuccess(

                        // If a new search, replace all elements with the new page
                        if (isNewSearch) Pagination.Result.Replace(newElements = dataItems)

                        // Else just append the new elements
                        else Pagination.Result.Append(
                            allElements = dataItems, newPage = movieUiModels
                        )
                    )
                }

                is DataResult.Failure -> emitFailure(apiResult.ex)
            }
        }

    companion object {
        private const val DEFAULT_PAGE = 0
    }
}
