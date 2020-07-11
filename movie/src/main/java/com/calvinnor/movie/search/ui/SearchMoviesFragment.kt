package com.calvinnor.movie.search.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.observe
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.core.pagination.PaginationListener
import com.calvinnor.core.ui.BaseFragment
import com.calvinnor.movie.R
import com.calvinnor.movie.search.di.SearchMoviesModule
import com.calvinnor.movie.search.viewmodel.SearchMoviesViewModel
import kotlinx.android.synthetic.main.fragment_search_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMoviesFragment : BaseFragment(R.layout.fragment_search_movies), PaginationListener {

    override val fragmentTag = TAG

    private val viewModel: SearchMoviesViewModel by viewModel()
    private val searchMoviesAdapter = SearchMoviesAdapter(this)

    init {
        SearchMoviesModule.load()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupAdapter()
        setupListeners()
        focusOnSearch()
    }

    override fun onNewRequest(request: Pagination.Request) {
        viewModel.paginateMovies(searchQuery = svMovies.query.toString())
    }

    override fun onReplacedData() {
        rvSearch.scheduleLayoutAnimation()
    }

    private fun setupUi() {
        svMovies.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?) = true

            override fun onQueryTextSubmit(query: String?): Boolean {
                return query?.let { viewModel.searchMovies(searchQuery = it); true } ?: false
            }
        })
    }

    private fun setupAdapter() {
        rvSearch.adapter = searchMoviesAdapter
    }

    private fun setupListeners() {
        observe(viewModel.searchMovies) {
            when (it) {

                is Result.Loading -> {
                    searchMoviesAdapter.showLoading()
                }

                is Result.Success -> {
                    searchMoviesAdapter.setResult(it.data)
                }

                is Result.Failure -> showError(it.ex)
            }
        }
    }

    private fun focusOnSearch() = svMovies.requestFocus()

    private fun showError(ex: Throwable) {
        // TODO
    }

    companion object {
        const val TAG = "SearchMoviesFragment"
    }
}
