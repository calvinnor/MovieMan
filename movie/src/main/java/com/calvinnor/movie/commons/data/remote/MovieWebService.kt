package com.calvinnor.movie.commons.data.remote

import com.calvinnor.movie.commons.data.Movie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieWebService {

    @GET("/3/movie/{movieId}")
    fun getMovie(
        @Path("movieId") movieId: String

    ): Deferred<Movie>

}
