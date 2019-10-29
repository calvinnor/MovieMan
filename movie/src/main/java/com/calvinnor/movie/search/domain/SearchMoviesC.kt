package com.calvinnor.movie.search.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.DataResult
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.data.movie.remote.api.MovieListingEnvelope
import com.calvinnor.movie.commons.model.MovieUiModel
import kotlinx.coroutines.flow.Flow

interface SearchMoviesC {

    interface Repository {

        suspend fun searchMovies(
            searchQuery: String,
            isNewSearch: Boolean
        ): Flow<Result<Pagination.Result<MovieUiModel>>>

    }

    interface Local

    interface Remote {

        suspend fun searchMovies(
            searchQuery: String,
            requestPage: Int = 1
        ): DataResult<MovieListingEnvelope>
    }
}
