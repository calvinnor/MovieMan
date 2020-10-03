package com.calvinnor.data.movie.remote

import com.calvinnor.data.movie.remote.api.MovieListingEnvelope
import com.calvinnor.data.movie.remote.api.MovieR
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieWebService {

    @GET("/3/movie/{movieId}")
    suspend fun getMovieAsync(
        @Path("movieId") movieId: String

    ): MovieR

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(@Query("page") requestPage: Int): MovieListingEnvelope

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") requestPage: Int): MovieListingEnvelope

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") requestPage: Int): MovieListingEnvelope

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") requestPage: Int): MovieListingEnvelope

    @GET("/3/search/movie")
    suspend fun searchMoviesAsync(
        @Query("query") searchQuery: String,
        @Query("page") requestPage: Int

    ): MovieListingEnvelope

}
