package com.calvinnor.movie.discover.ui

import android.os.Bundle
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.observe
import com.calvinnor.core.ui.BaseActivity
import com.calvinnor.movie.R
import com.calvinnor.movie.discover.viewmodel.DiscoverMoviesViewModel
import kotlinx.android.synthetic.main.activity_discover_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverMoviesActivity : BaseActivity() {

    override val contentLayout = R.layout.activity_discover_movies

    private val viewModel: DiscoverMoviesViewModel by viewModel()
    private val discoverMoviesAdapter = DiscoverMoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupAdapter()
        setupListeners()
        fetchData()
    }

    private fun setupAdapter() {
        rvDiscover.adapter = discoverMoviesAdapter
    }

    private fun setupListeners() {
        viewModel.discoverMovies.observe(this) {
            when (it) {
                is Result.Loading -> showLoading(true)

                is Result.Success -> {
                    showLoading(false)
                    discoverMoviesAdapter.setItems(it.data)
                }

                is Result.Failure -> showError(it.ex)
            }
        }
    }

    private fun fetchData() {
        viewModel.getMovieDetails()
    }

    private fun showError(ex: Throwable) {
        // TODO
    }

    private fun showLoading(isLoading: Boolean) {
        // TODO
    }
}
