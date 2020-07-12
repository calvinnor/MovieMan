package com.calvinnor.movie.search.domain

import com.calvinnor.core.networking.DataResult
import com.calvinnor.core.networking.callApi
import com.calvinnor.data.movie.remote.MovieWebService
import com.calvinnor.data.movie.remote.api.MovieListingEnvelope

class SearchMoviesRemote(
    private val movieWebService: MovieWebService

) : SearchMoviesC.Remote {

    override suspend fun searchMovies(
        searchQuery: String,
        requestPage: Int
    ): DataResult<MovieListingEnvelope> =
        callApi {
            movieWebService.searchMoviesAsync(
                searchQuery = searchQuery,
                requestPage = requestPage
            )
        }

}
