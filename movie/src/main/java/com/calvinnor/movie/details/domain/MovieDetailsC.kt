package com.calvinnor.movie.details.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.DataResult
import com.calvinnor.data.movie.local.entities.MovieL
import com.calvinnor.data.movie.remote.api.MovieR
import com.calvinnor.movie.details.model.MovieDetailsUiModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailsC {

    interface Repository {
        suspend fun getMovieDetails(movieId: String): Flow<Result<MovieDetailsUiModel>>
    }

    interface Local {
        suspend fun saveMovieDetails(movie: MovieL)
        suspend fun getMovieDetails(movieId: String): DataResult<MovieL>
    }

    interface Remote {
        suspend fun getMovieDetails(movieId: String): DataResult<MovieR>
    }

}
