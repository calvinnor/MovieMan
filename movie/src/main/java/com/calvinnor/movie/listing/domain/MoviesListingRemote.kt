package com.calvinnor.movie.listing.domain

import com.calvinnor.core.networking.callApi
import com.calvinnor.data.movie.remote.MovieWebService

class MoviesListingRemote(
    private val movieWebService: MovieWebService

) : MoviesListingC.Remote {

    override suspend fun getPopularMovies(requestPage: Int) =
        callApi { movieWebService.getPopularMovies(requestPage) }

    override suspend fun getNowPlayingMovies(requestPage: Int) =
        callApi { movieWebService.getNowPlayingMovies(requestPage) }

    override suspend fun getTopRatedMovies(requestPage: Int) =
        callApi { movieWebService.getTopRatedMovies(requestPage) }

    override suspend fun getUpcomingMovies(requestPage: Int) =
        callApi { movieWebService.getUpcomingMovies(requestPage) }

}
