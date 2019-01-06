package com.calvinnor.movie.details.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.ApiResult
import com.calvinnor.data.movie.local.entities.MovieL
import com.calvinnor.movie.details.model.MovieDetailsUiModel

class MovieDetailsRepo(
    private val local: MovieDetailsC.Local,
    private val remote: MovieDetailsC.Remote

) : MovieDetailsC.Repository {

    override suspend fun getMovieDetails(movieId: String): Result<MovieDetailsUiModel> {
        val localResult = local.getMovieDetails(movieId = movieId)
        if (localResult is Result.Success) return Result.Success(MovieDetailsUiModel(localResult.data))

        val remoteResult = remote.getMovieDetails(movieId = movieId)
        if (remoteResult is ApiResult.Success) { // Save to DB
            local.saveMovieDetails(MovieL(remoteResult.result))
        }

        return when (remoteResult) {
            is ApiResult.Success -> Result.Success(MovieDetailsUiModel(remoteResult.result))
            is ApiResult.Failure -> Result.Failure(remoteResult.ex)
        }
    }
}
