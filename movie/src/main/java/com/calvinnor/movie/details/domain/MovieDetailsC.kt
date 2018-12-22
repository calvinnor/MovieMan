package com.calvinnor.movie.details.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.ApiResult
import com.calvinnor.movie.commons.data.Movie
import com.calvinnor.movie.details.model.MovieDetailsUiModel

interface MovieDetailsC {

    interface Repository {

        suspend fun getMovieDetails(movieId: String): Result<MovieDetailsUiModel>

    }

    interface Local

    interface Remote {

        suspend fun getMovieDetails(movieId: String): ApiResult<Movie>

    }

}
