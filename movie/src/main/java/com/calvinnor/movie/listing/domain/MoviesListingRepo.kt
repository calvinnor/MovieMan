package com.calvinnor.movie.listing.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.emitFailure
import com.calvinnor.core.extensions.emitSuccess
import com.calvinnor.core.networking.DataResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.movie.commons.model.MoviesSection
import com.calvinnor.movie.commons.model.MovieUiModel
import kotlinx.coroutines.flow.flow

class MoviesListingRepo(
    private val remote: MoviesListingC.Remote

) : MoviesListingC.Repository {

    private var currentPage: Int = 0
    private var dataItems: MutableList<MovieUiModel> = mutableListOf()

    override suspend fun getMovies(section: MoviesSection) =
        flow<Result<Pagination.Result<MovieUiModel>>> {
            val nextPage = currentPage + 1
            val apiResult = when (section) {
                MoviesSection.POPULAR -> remote.getPopularMovies(nextPage)
                MoviesSection.TOP_RATED -> remote.getTopRatedMovies(nextPage)
                MoviesSection.UPCOMING -> remote.getUpcomingMovies(nextPage)
                MoviesSection.NOW_PLAYING -> remote.getNowPlayingMovies(nextPage)
            }

            when (apiResult) {

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
