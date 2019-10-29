package com.calvinnor.movie.discover.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.DataResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.data.movie.remote.api.MovieListingEnvelope
import com.calvinnor.movie.commons.model.MovieUiModel
import kotlinx.coroutines.flow.Flow

interface DiscoverMoviesC {

    interface Repository {
        suspend fun getPopularMovies(): Flow<Result<Pagination.Result<MovieUiModel>>>
    }

    interface Local

    interface Remote {
        suspend fun getPopularMovies(requestPage: Int = 1): DataResult<MovieListingEnvelope>
    }
}
