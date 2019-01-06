package com.calvinnor.movie.details.domain

import com.calvinnor.core.networking.callApi
import com.calvinnor.data.movie.remote.MovieWebService

class MovieDetailsRemote(
    private val movieWebService: MovieWebService

) : MovieDetailsC.Remote {

    override suspend fun getMovieDetails(movieId: String) =
        callApi(movieWebService.getMovie(movieId = movieId))

}
