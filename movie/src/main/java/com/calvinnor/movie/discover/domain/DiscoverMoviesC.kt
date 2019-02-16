package com.calvinnor.movie.discover.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.ApiResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.data.movie.remote.api.MovieListingEnvelope
import com.calvinnor.movie.discover.model.MovieUiModel

interface DiscoverMoviesC {

    interface Repository {
        suspend fun getPopularMovies(offset: Long = 0L): Result<Pagination.Result<MovieUiModel>>
    }

    interface Local

    interface Remote {
        suspend fun getPopularMovies(requestPage: Int = 1): ApiResult<MovieListingEnvelope>
    }
}
