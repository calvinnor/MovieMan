package com.calvinnor.movie.details.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.emitFailure
import com.calvinnor.core.extensions.emitSuccess
import com.calvinnor.core.networking.DataResult
import com.calvinnor.data.movie.local.entities.MovieL
import com.calvinnor.movie.details.model.MovieDetailsUiModel
import kotlinx.coroutines.flow.flow

class MovieDetailsRepo(
    private val local: MovieDetailsC.Local,
    private val remote: MovieDetailsC.Remote

) : MovieDetailsC.Repository {

    override suspend fun getMovieDetails(movieId: String) = flow<Result<MovieDetailsUiModel>> {
        val localResult = local.getMovieDetails(movieId = movieId)
        if (localResult is DataResult.Success) {
            emitSuccess(MovieDetailsUiModel(localResult.data))
            return@flow
        }

        val remoteResult = remote.getMovieDetails(movieId = movieId)
        if (remoteResult is DataResult.Success) { // Save to DB
            local.saveMovieDetails(MovieL(remoteResult.data))
        }

        when (remoteResult) {
            is DataResult.Success -> emitSuccess(MovieDetailsUiModel(remoteResult.data))
            is DataResult.Failure -> emitFailure(remoteResult.ex)
        }
    }
}
