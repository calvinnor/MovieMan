package com.calvinnor.movie.commons.data.remote

import com.calvinnor.movie.commons.data.Movie
import com.calvinnor.movie.commons.data.MovieListingEnvelope
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieWebService {

    @GET("/3/movie/{movieId}")
    fun getMovie(
        @Path("movieId") movieId: String

    ): Deferred<Movie>

    @GET("/3/discover/movie?sort_by=popularity.desc")
    fun discoverPopularMovies(): Deferred<MovieListingEnvelope>
}
