package com.calvinnor.movie.discover.domain

import com.calvinnor.core.networking.callApi
import com.calvinnor.movie.commons.data.remote.MovieWebService

class DiscoverMoviesRemote(
    private val movieWebService: MovieWebService

) : DiscoverMoviesC.Remote {

    override suspend fun getPopularMovies() = callApi(movieWebService.discoverPopularMovies())

}
