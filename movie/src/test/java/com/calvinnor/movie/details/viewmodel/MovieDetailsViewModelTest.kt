package com.calvinnor.movie.details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.calvinnor.core.domain.Result
import com.calvinnor.core.exceptions.NoDataException
import com.calvinnor.core.testing.buildTestingDispatcher
import com.calvinnor.movie.details.domain.MovieDetailsC
import com.calvinnor.movie.details.model.MovieDetailsUiModel
import com.calvinnor.movie.commons.model.MovieUiModel
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Tests the interaction between ViewModel and Repo.
 *
 * Tests the LiveData emissions to the UI layer.
 */
class MovieDetailsViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val observer: Observer<Result<MovieDetailsUiModel>> = mock()
    private val repo: MovieDetailsC.Repository = mock()

    private val viewModel = MovieDetailsViewModel(repo, buildTestingDispatcher())

    @Test
    fun whenGetMovieDetails_thenLoadingIsEmitted() {
        mockRepoSuccess()

        // Observe the LiveData
        viewModel.movieDetails.observeForever(observer)

        // When the ViewModel is called
        viewModel.getMovieDetails("2")

        // Then a Loading is emitted
        verify(observer).onChanged(Result.Loading())
    }

    @Test
    fun whenGetMovieDetails_thenUiModelIsEmitted() {
        mockRepoSuccess()

        // Observe the LiveData
        viewModel.movieDetails.observeForever(observer)

        // When the ViewModel is called
        viewModel.getMovieDetails("2")

        // Then a Success is emitted
        assert(viewModel.movieDetails.value is Result.Success)

        // with the correct data
        assertEquals(Result.Success(TEST_UI_MODEL), viewModel.movieDetails.value)
    }

    @Test
    fun whenGetMovieDetails_andHasData_thenLoadingIsNotEmitted() {
        runBlocking {
            mockRepoSuccess()

            // Observe the LiveData
            viewModel.movieDetails.observeForever(observer)

            // When the ViewModel is called
            viewModel.getMovieDetails("2")

            // Then a Success is emitted
            assert(viewModel.movieDetails.value is Result.Success)

            // And when ViewModel is called again
            viewModel.getMovieDetails("2")

            // Then Loading is emitted only once
            verify(observer, times(1)).onChanged(Result.Loading())

            // And Repo is called only once
            verify(repo, times(1)).getMovieDetails("2")
        }
    }

    @Test
    fun whenGetMovieDetails_andNoCacheOrNetwork_thenFailureIsEmitted() {
        mockRepoFailure()

        // Observe the LiveData
        viewModel.movieDetails.observeForever(observer)

        // When the ViewModel is called
        viewModel.getMovieDetails("2")

        // Then a Failure is emitted
        assert(viewModel.movieDetails.value is Result.Failure)

        // with the correct exception
        assertEquals(Result.Failure<MovieUiModel>(TEST_EXCEPTION), viewModel.movieDetails.value)
    }

    private fun mockRepoSuccess() = runBlocking {
        whenever(repo.getMovieDetails("2")) doReturn (Result.Success(TEST_UI_MODEL))
    }

    private fun mockRepoFailure() = runBlocking {
        whenever(repo.getMovieDetails("2")) doReturn (Result.Failure(TEST_EXCEPTION))
    }

    companion object {

        private val TEST_UI_MODEL = MovieDetailsUiModel(
            title = "Hello World",
            description = "A new program written in Kotlin emerges",
            backdropImage = "https://kotlinlang.org/assets/images/open-graph/kotlin_250x250.png",
            posterImage = "https://kotlinlang.org/assets/images/open-graph/kotlin_250x250.png",
            releaseYear = "2019"
        )

        private val TEST_EXCEPTION = NoDataException()
    }
}
