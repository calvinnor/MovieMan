package com.calvinnor.movie.details.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.ApiResult
import com.calvinnor.movie.details.model.MovieDetailsUiModel

class MovieDetailsRepo(
    private val remote: MovieDetailsC.Remote

) : MovieDetailsC.Repository {

    override suspend fun getMovieDetails(movieId: String): Result<MovieDetailsUiModel> {
        return remote.getMovieDetails(movieId = movieId).let {
            when (it) {
                is ApiResult.Success -> Result.Success(MovieDetailsUiModel(it.result))
                is ApiResult.Failure -> Result.Failure(it.ex)
            }
        }
    }
}