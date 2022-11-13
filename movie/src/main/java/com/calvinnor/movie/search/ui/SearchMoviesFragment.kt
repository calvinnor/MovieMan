package com.calvinnor.movie.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.closeKeyboard
import com.calvinnor.core.extensions.focusAndOpenKeyboard
import com.calvinnor.core.extensions.observe
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.core.pagination.PaginationListener
import com.calvinnor.core.ui.BaseFragment
import com.calvinnor.movie.databinding.FragmentSearchMoviesBinding
import com.calvinnor.movie.search.di.SearchMoviesModule
import com.calvinnor.movie.search.viewmodel.SearchMoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMoviesFragment : BaseFragment<FragmentSearchMoviesBinding>(), PaginationListener {

    override val fragmentTag = TAG

    private val viewModel: SearchMoviesViewModel by viewModel()
    private val searchMoviesAdapter = SearchMoviesAdapter(this)

    init {
        SearchMoviesModule.load()
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchMoviesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupAdapter()
        setupListeners()
        focusOnSearch()
    }

    override fun onNewRequest(request: Pagination.Request) {
        viewModel.paginateMovies(searchQuery = viewBinding.svMovies.query.toString())
    }

    override fun onReplacedData() {
        viewBinding.rvSearch.scheduleLayoutAnimation()
    }

    private fun setupUi() = with(viewBinding) {
        svMovies.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?) = true

            override fun onQueryTextSubmit(query: String?): Boolean {
                closeKeyboard()
                return query?.let { viewModel.searchMovies(searchQuery = it); true } ?: false
            }
        })
    }

    private fun setupAdapter() {
        viewBinding.rvSearch.adapter = searchMoviesAdapter
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

    private fun focusOnSearch() = viewBinding.svMovies.focusAndOpenKeyboard()

    private fun showError(ex: Throwable) {
        // TODO
    }

    companion object {
        const val TAG = "SearchMoviesFragment"
    }
}
