package com.calvinnor.movieman.data.remote

import com.calvinnor.movieman.BuildConfig.API_KEY
import com.calvinnor.movieman.commons.Header
import com.calvinnor.movieman.data.Movie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieWebService {

    @GET("/3/movie/{movieId}")
    fun getMovie(
        @Path("movieId") movieId: String,
        @Query(Header.API_KEY) apiKey: String = API_KEY

    ): Deferred<Movie>

}
