package com.calvinnor.movie.discover.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.networking.ApiResult
import com.calvinnor.movie.discover.model.MovieUiModel

class DiscoverMoviesRepo(
    private val remote: DiscoverMoviesC.Remote

) : DiscoverMoviesC.Repository {

    override suspend fun getPopularMovies(): Result<List<MovieUiModel>> {
        return remote.getPopularMovies().let {
            when (it) {
                is ApiResult.Success -> Result.Success(it.result.results.map { MovieUiModel(it) })
                is ApiResult.Failure -> Result.Failure(it.ex)
            }
        }
    }
}
