package com.calvinnor.movie.details.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.ApiResult
import com.calvinnor.data.movie.local.entities.MovieL
import com.calvinnor.data.movie.remote.api.MovieR
import com.calvinnor.movie.details.model.MovieDetailsUiModel

interface MovieDetailsC {

    interface Repository {
        suspend fun getMovieDetails(movieId: String): Result<MovieDetailsUiModel>
    }

    interface Local {
        suspend fun saveMovieDetails(movie: MovieL)
        suspend fun getMovieDetails(movieId: String): Result<MovieL>
    }

    interface Remote {
        suspend fun getMovieDetails(movieId: String): ApiResult<MovieR>
    }

}
