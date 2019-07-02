package com.calvinnor.movie.search.domain

import com.calvinnor.core.networking.ApiResult
import com.calvinnor.core.networking.callApi
import com.calvinnor.data.movie.remote.MovieWebService
import com.calvinnor.data.movie.remote.api.MovieListingEnvelope

class SearchMoviesRemote(
    private val movieWebService: MovieWebService

) : SearchMoviesC.Remote {

    override suspend fun searchMovies(
        searchQuery: String,
        requestPage: Int
    ): ApiResult<MovieListingEnvelope> =
        callApi(
            movieWebService.searchMovies(
                searchQuery = searchQuery,
                requestPage = requestPage
            )
        )

}
