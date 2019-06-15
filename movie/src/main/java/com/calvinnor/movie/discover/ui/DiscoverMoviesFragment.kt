package com.calvinnor.movie.discover.ui

import android.os.Bundle
import android.view.View
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.observe
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.core.pagination.PaginationListener
import com.calvinnor.core.ui.BaseFragment
import com.calvinnor.movie.R
import com.calvinnor.movie.discover.di.DiscoverMoviesModule
import com.calvinnor.movie.discover.viewmodel.DiscoverMoviesViewModel
import kotlinx.android.synthetic.main.fragment_discover_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverMoviesFragment : BaseFragment(), PaginationListener {

    override val fragmentTag = TAG
    override val layout = R.layout.fragment_discover_movies

    private val viewModel: DiscoverMoviesViewModel by viewModel()
    private val discoverMoviesAdapter = DiscoverMoviesAdapter(this)

    init {
        DiscoverMoviesModule.load()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupListeners()
        fetchData()
    }

    override fun onNewRequest(request: Pagination.Request) {
        viewModel.getMoreMovies()
    }

    private fun setupAdapter() {
        rvDiscover.adapter = discoverMoviesAdapter
    }

    private fun setupListeners() {
        observe(viewModel.discoverMovies) {
            when (it) {

                is Result.Success -> {
                    discoverMoviesAdapter.setResult(it.data)
                }

                is Result.Failure -> showError(it.ex)
            }
        }
    }

    private fun fetchData() {
        viewModel.getMovies()
    }

    private fun showError(ex: Throwable) {
        // TODO
    }

    companion object {
        const val TAG = "DiscoverMoviesFragment"
    }
}
