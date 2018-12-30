package com.calvinnor.movie.discover.ui

import android.os.Bundle
import android.view.View
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.observe
import com.calvinnor.core.ui.BaseFragment
import com.calvinnor.movie.R
import com.calvinnor.movie.discover.viewmodel.DiscoverMoviesViewModel
import kotlinx.android.synthetic.main.fragment_discover_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverMoviesFragment : BaseFragment() {

    override val fragmentTag = TAG
    override val layout = R.layout.fragment_discover_movies

    private val viewModel: DiscoverMoviesViewModel by viewModel()
    private val discoverMoviesAdapter = DiscoverMoviesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    companion object {
        const val TAG = "DiscoverMoviesFragment"
    }
}
