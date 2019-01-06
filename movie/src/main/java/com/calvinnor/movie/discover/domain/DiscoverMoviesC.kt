package com.calvinnor.movie.discover.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.ApiResult
import com.calvinnor.data.movie.remote.api.MovieListingEnvelope
import com.calvinnor.movie.discover.model.MovieUiModel

interface DiscoverMoviesC {

    interface Repository {
        suspend fun getPopularMovies(): Result<List<MovieUiModel>>
    }

    interface Local

    interface Remote {
        suspend fun getPopularMovies(): ApiResult<MovieListingEnvelope>
    }
}
