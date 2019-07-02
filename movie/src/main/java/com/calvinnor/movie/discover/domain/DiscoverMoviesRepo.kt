package com.calvinnor.movie.discover.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.ApiResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.movie.commons.model.MovieUiModel

class DiscoverMoviesRepo(
    private val remote: DiscoverMoviesC.Remote

) : DiscoverMoviesC.Repository {

    private var currentPage: Int = 0
    private var dataItems: MutableList<MovieUiModel> = mutableListOf()

    override suspend fun getPopularMovies(): Result<Pagination.Result<MovieUiModel>> {
        return remote.getPopularMovies(currentPage + 1).let { apiResult ->
            when (apiResult) {

                is ApiResult.Success -> {
                    val movieUiModels = apiResult.result.results.map { MovieUiModel(it) }

                    currentPage = apiResult.result.page
                    dataItems.addAll(movieUiModels)

                    // Post Pagination Success
                    Result.Success(
                        Pagination.Result.Append(
                            allElements = dataItems,
                            newPage = movieUiModels
                        )
                    )
                }

                is ApiResult.Failure -> Result.Failure(apiResult.ex)
            }
        }
    }
}
