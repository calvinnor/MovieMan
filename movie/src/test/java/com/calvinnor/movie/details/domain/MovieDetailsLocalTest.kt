package com.calvinnor.movie.details.domain

import com.calvinnor.core.domain.Result
import com.calvinnor.core.exceptions.NoDataException
import com.calvinnor.core.networking.DataResult
import com.calvinnor.data.movie.local.dao.MovieDao
import com.calvinnor.data.movie.local.entities.MovieL
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Tests the interaction between Local layer and Room.
 *
 * Tests the results from Local functions via [Result]
 */
class MovieDetailsLocalTest {

    private val dao: MovieDao = mock()
    private val local = MovieDetailsLocal(dao)

    @Test
    fun whenGetMovieDetails_thenReturnSuccess() = runBlocking {
        mockMovieCached()

        // When we call Local
        val movieData = local.getMovieDetails("2")

        // Then we get a Success
        assert(movieData is DataResult.Success<MovieL>)

        // with the correct data
        if (movieData is DataResult.Success<MovieL>)
            assertEquals(TEST_MOVIE, movieData.data)
    }

    @Test
    fun whenGetMovieDetails_thenReturnFailure() = runBlocking {
        mockMovieUnavailable()

        // When we call Local
        val movieData = local.getMovieDetails("2")

        // Then we get a failure
        assert(movieData is DataResult.Failure<MovieL>)

        // with the correct exception
        if (movieData is DataResult.Failure<MovieL>)
            assertEquals(TEST_EXCEPTION, movieData.ex)
    }

    @Test
    fun whenSaveMovie_thenCallDao() = runBlocking {

        // When we call Local
        local.saveMovieDetails(TEST_MOVIE)

        // Then Dao is called
        verify(dao).insert(TEST_MOVIE)
    }

    private fun mockMovieCached() {
        whenever(dao.getMovieDetails("2")) doReturn (TEST_MOVIE)
    }

    private fun mockMovieUnavailable() {
        whenever(dao.getMovieDetails("2")) doReturn (null)
    }

    companion object {
        private val TEST_MOVIE = MovieL(
            id = 2,
            title = "Hello World",
            overview = "A new program written in Kotlin emerges",
            backdropPath = "https://kotlinlang.org/assets/images/open-graph/kotlin_250x250.png",
            posterPath = "https://kotlinlang.org/assets/images/open-graph/kotlin_250x250.png",
            releaseDate = Date()
        )

        private val TEST_EXCEPTION = NoDataException()
    }
}
