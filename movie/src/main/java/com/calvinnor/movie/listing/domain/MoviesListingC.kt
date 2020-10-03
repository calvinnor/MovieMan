package com.calvinnor.movie.listing.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.DataResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.data.movie.remote.api.MovieListingEnvelope
import com.calvinnor.movie.commons.model.MoviesSection
import com.calvinnor.movie.commons.model.MovieUiModel
import kotlinx.coroutines.flow.Flow

interface MoviesListingC {

    interface Repository {
        suspend fun getMovies(section: MoviesSection): Flow<Result<Pagination.Result<MovieUiModel>>>
    }

    interface Local

    interface Remote {
        suspend fun getPopularMovies(requestPage: Int = 1): DataResult<MovieListingEnvelope>
        suspend fun getNowPlayingMovies(requestPage: Int = 1): DataResult<MovieListingEnvelope>
        suspend fun getTopRatedMovies(requestPage: Int = 1): DataResult<MovieListingEnvelope>
        suspend fun getUpcomingMovies(requestPage: Int = 1): DataResult<MovieListingEnvelope>
    }
}
