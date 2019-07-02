package com.calvinnor.data.movie.remote

import com.calvinnor.data.movie.remote.api.MovieListingEnvelope
import com.calvinnor.data.movie.remote.api.MovieR
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieWebService {

    @GET("/3/movie/{movieId}")
    fun getMovie(
        @Path("movieId") movieId: String

    ): Deferred<MovieR>

    @GET("/3/discover/movie?sort_by=popularity.desc")
    fun discoverPopularMovies(
        @Query("page") requestPage: Int

    ): Deferred<MovieListingEnvelope>

    @GET("/3/search/movie")
    fun searchMovies(
        @Query("query") searchQuery: String,
        @Query("page") requestPage: Int

    ): Deferred<MovieListingEnvelope>

}
