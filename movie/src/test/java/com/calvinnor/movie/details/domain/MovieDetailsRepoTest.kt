package com.calvinnor.movie.details.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.exceptions.NoDataException
import com.calvinnor.core.networking.ApiResult
import com.calvinnor.data.movie.local.entities.MovieL
import com.calvinnor.data.movie.remote.api.MovieR
import com.calvinnor.movie.details.model.MovieDetailsUiModel
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Tests the interaction between Repo, Local & Remote.
 *
 * Tests the results from Repo via [Result]
 */
class MovieDetailsRepoTest {

    private val local: MovieDetailsC.Local = mock()
    private val remote: MovieDetailsC.Remote = mock()

    private val repo = MovieDetailsRepo(local, remote)

    @Test
    fun whenGetMovieDetails_andCached_thenReturnCached() = runBlocking {
        mockMovieCachedLocally()

        // When we call Repo
        val movieData = repo.getMovieDetails("2")

        // Then we get a Success
        assert(movieData is Result.Success<MovieDetailsUiModel>)

        // with the correct data
        if (movieData is Result.Success<MovieDetailsUiModel>)
            assertEquals(MovieDetailsUiModel(TEST_LOCAL_MOVIE), movieData.data)
    }

    @Test
    fun whenGetMovieDetails_andCached_thenRemoteIsNotCalled() {
        runBlocking {
            mockMovieCachedLocally()

            // When we call Repo
            repo.getMovieDetails("2")

            // Then Remote is not called
            verify(remote, never()).getMovieDetails("2")
        }
    }

    @Test
    fun whenGetMovieDetails_andNotCached_thenReturnRemote() = runBlocking {
        mockMovieNotCachedLocally()
        mockRemoteMovieSucces()

        // When we call Repo
        val movieData = repo.getMovieDetails("2")

        // Then Remote is called
        verify(remote).getMovieDetails("2")

        // and Success is returned
        assert(movieData is Result.Success<MovieDetailsUiModel>)

        // with the correct data
        if (movieData is Result.Success<MovieDetailsUiModel>)
            assertEquals(MovieDetailsUiModel(TEST_REMOTE_MOVIE), movieData.data)
    }

    @Test
    fun whenGetMovieDetails_andNotCached_thenCachedLocally() = runBlocking {
        mockMovieNotCachedLocally()
        mockRemoteMovieSucces()

        // When we call Repo
        repo.getMovieDetails("2")

        // Then Remote is called
        verify(remote).getMovieDetails("2")

        // And result is saved
        verify(local).saveMovieDetails(TEST_LOCAL_MOVIE)
    }

    @Test
    fun whenGetMovieDetails_andNotCached_andRemoteFails_thenReturnFailure() = runBlocking {
        mockMovieNotCachedLocally()
        mockRemoteMovieFailure()

        // When we call Repo
        val movieData = repo.getMovieDetails("2")

        // Then we get a Failure
        assert(movieData is Result.Failure)

        // with the correct exception
        if (movieData is Result.Failure)
            assertEquals(TEST_EXCEPTION, movieData.ex)
    }

    private fun mockMovieCachedLocally() = runBlocking {
        whenever(local.getMovieDetails("2")) doReturn (Result.Success(TEST_LOCAL_MOVIE))
    }

    private fun mockMovieNotCachedLocally() = runBlocking {
        whenever(local.getMovieDetails("2")) doReturn (Result.Failure(TEST_EXCEPTION))
    }

    private fun mockRemoteMovieSucces() = runBlocking {
        whenever(remote.getMovieDetails("2")) doReturn (ApiResult.Success(TEST_REMOTE_MOVIE))
    }

    private fun mockRemoteMovieFailure() = runBlocking {
        whenever(remote.getMovieDetails("2")) doReturn (ApiResult.Failure(TEST_EXCEPTION))
    }

    companion object {

        private val TEST_DATE = Date()

        private val TEST_REMOTE_MOVIE = MovieR(
            id = 2,
            title = "Hello World",
            overview = "A new program written in Kotlin emerges",
            backdropPath = "https://kotlinlang.org/backdrop.png",
            posterPath = "https://kotlinlang.org/poster.png",
            releaseDate = TEST_DATE
        )

        private val TEST_LOCAL_MOVIE = MovieL(
            id = 2,
            title = "Hello World",
            overview = "A new program written in Kotlin emerges",
            backdropPath = "https://kotlinlang.org/backdrop.png",
            posterPath = "https://kotlinlang.org/poster.png",
            releaseDate = TEST_DATE
        )

        private val TEST_EXCEPTION = NoDataException()
    }
}
