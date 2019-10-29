package com.calvinnor.movie.discover.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.emitFailure
import com.calvinnor.core.extensions.emitSuccess
import com.calvinnor.core.networking.DataResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.movie.commons.model.MovieUiModel
import kotlinx.coroutines.flow.flow

class DiscoverMoviesRepo(
    private val remote: DiscoverMoviesC.Remote

) : DiscoverMoviesC.Repository {

    private var currentPage: Int = 0
    private var dataItems: MutableList<MovieUiModel> = mutableListOf()

    override suspend fun getPopularMovies() = flow<Result<Pagination.Result<MovieUiModel>>> {
        when (val apiResult = remote.getPopularMovies(currentPage + 1)) {

            is DataResult.Success -> {
                val movieUiModels = apiResult.data.results.map { MovieUiModel(it) }

                currentPage = apiResult.data.page
                dataItems.addAll(movieUiModels)

                // Post Pagination Success
                emitSuccess(
                    Pagination.Result.Append(
                        allElements = dataItems,
                        newPage = movieUiModels
                    )
                )
            }

            is DataResult.Failure -> emitFailure(apiResult.ex)
        }
    }
}
