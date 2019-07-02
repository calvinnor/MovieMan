package com.calvinnor.movie.search.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.ApiResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.movie.commons.model.MovieUiModel

class SearchMoviesRepo(
    private val remote: SearchMoviesC.Remote

) : SearchMoviesC.Repository {

    private var currentPage: Int = DEFAULT_PAGE
    private var dataItems: MutableList<MovieUiModel> = mutableListOf()

    override suspend fun searchMovies(
        searchQuery: String,
        isNewSearch: Boolean
    ): Result<Pagination.Result<MovieUiModel>> {

        if (isNewSearch) {
            currentPage = DEFAULT_PAGE
            dataItems.clear()
        }

        return remote.searchMovies(
            searchQuery = searchQuery,
            requestPage = currentPage + 1
        ).let { apiResult ->

            when (apiResult) {

                is ApiResult.Success -> {
                    val movieUiModels = apiResult.result.results.map { MovieUiModel(it) }

                    currentPage = apiResult.result.page
                    dataItems.addAll(movieUiModels)

                    // If a new search, replace all elements with the new page
                    if (isNewSearch)
                        Result.Success(
                            Pagination.Result.Replace(
                                newElements = dataItems
                            )
                        )

                    // Else just append the new elements
                    else
                        Result.Success(
                            Pagination.Result.Append(
                                allElements = dataItems, newPage = movieUiModels
                            )
                        )
                }

                is ApiResult.Failure -> Result.Failure(apiResult.ex)
            }
        }
    }

    companion object {
        private const val DEFAULT_PAGE = 0
    }
}
