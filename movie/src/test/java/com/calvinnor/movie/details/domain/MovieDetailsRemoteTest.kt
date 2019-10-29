package com.calvinnor.movie.details.domain

import com.calvinnor.core.networking.DataResult
import com.calvinnor.data.movie.remote.MovieWebService
import com.calvinnor.data.movie.remote.api.MovieR
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException
import java.util.*

/**
 * Tests the interaction between Remote & WebService layer.
 *
 * Tests the results from Remote functions via [DataResult]
 */
class MovieDetailsRemoteTest {

    private val webService: MovieWebService = mock()
    private val remote = MovieDetailsRemote(webService)

    @Test
    fun whenGetMovie_thenReturnSuccess() = runBlocking {
        mockApiSuccess()

        // When API is called
        val apiResult = remote.getMovieDetails("2")

        // Then we get a Success
        assert(apiResult is DataResult.Success<MovieR>)

        // With the correct data
        if (apiResult is DataResult.Success<MovieR>)
            assertEquals(TEST_MOVIE, apiResult.data)
    }

    // TODO: Find how to mock an Exception throw
    // @Test
    fun whenGetMovie_thenReturnFailure() = runBlocking {
        mockApiFailure()

        // When API is called
        val apiResult = remote.getMovieDetails("2")

        // Then we get a Failure
        assert(apiResult is DataResult.Failure)

        // With the correct exception
        if (apiResult is DataResult.Failure)
            assertEquals(IOException("Bad Network"), apiResult.ex)
    }

    private fun mockApiSuccess() {
        whenever(webService.getMovie("2")) doReturn (CompletableDeferred(TEST_MOVIE))
    }

    private fun mockApiFailure() = runBlocking {
        whenever(webService.getMovie("2")) doThrow (NETWORK_EXCEPTION)
    }

    companion object {

        private val TEST_MOVIE = MovieR(
            id = 2,
            title = "Hello World",
            overview = "A new program written in Kotlin emerges",
            backdropPath = "https://kotlinlang.org/assets/images/open-graph/kotlin_250x250.png",
            posterPath = "https://kotlinlang.org/assets/images/open-graph/kotlin_250x250.png",
            releaseDate = Date()
        )

        private val NETWORK_EXCEPTION by lazy { IOException("Bad Network") }
    }
}
