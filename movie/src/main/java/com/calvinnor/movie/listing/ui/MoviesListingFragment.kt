package com.calvinnor.movie.listing.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.navigation.fragment.FragmentNavigator
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.observe
import com.calvinnor.core.pagination.Pagination
import com.calvinnor.core.ui.BaseFragment
import com.calvinnor.movie.R
import com.calvinnor.movie.commons.model.MovieUiModel
import com.calvinnor.movie.commons.model.MoviesSection
import com.calvinnor.movie.listing.di.SectionMoviesModule
import com.calvinnor.movie.listing.viewmodel.MoviesListingViewModel
import kotlinx.android.synthetic.main.fragment_movies_listing.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListingFragment : BaseFragment(R.layout.fragment_movies_listing),
    MoviesListingAdapter.MoviesListingInteractions {

    override val fragmentTag = TAG

    private val listingViewModel: MoviesListingViewModel by viewModel()
    private val moviesListingAdapter = MoviesListingAdapter(this)
    private lateinit var interactions: MoviesListingInteractions

    private val moviesSection: MoviesSection by lazy {
        arguments?.let { MoviesListingFragmentArgs.fromBundle(it).selectedSection }
            ?: throw IllegalArgumentException("Must pass movie section")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        interactions = when {
            context is MoviesListingInteractions -> context
            // Because NavHost is the parent, and DiscoverFragment is it's parent
            parentFragment?.parentFragment is MoviesListingInteractions -> parentFragment?.parentFragment as MoviesListingInteractions
            else -> throw IllegalArgumentException("Parent activity or fragment must implement MoviesSectionInteractions")
        }
    }

    override fun loadDependencies() = SectionMoviesModule.load()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyStatusBarInsets(view)
        setupAdapter()
        setupListeners()
        fetchData()
    }

    override fun onMovieSelected(model: MovieUiModel, navigatorExtras: FragmentNavigator.Extras) {
        interactions.onMovieSelected(model, navigatorExtras)
    }

    override fun onNewRequest(request: Pagination.Request) {
        listingViewModel.getMoreMovies(moviesSection)
    }

    override fun onReplacedData() {
        rvDiscover.scheduleLayoutAnimation()
    }

    private fun applyStatusBarInsets(rootView: View) = with(rootView) {
        setOnApplyWindowInsetsListener { _, windowInsets ->
            updatePadding(top = windowInsets.systemWindowInsetTop)
            return@setOnApplyWindowInsetsListener windowInsets
        }
        requestApplyInsets()
    }

    private fun setupAdapter() {
        rvDiscover.adapter = moviesListingAdapter
    }

    private fun setupListeners() {
        observe(listingViewModel.discoverMovies) {
            when (it) {

                is Result.Loading -> {
                    moviesListingAdapter.showLoading()
                }

                is Result.Success -> {
                    moviesListingAdapter.setResult(it.data)
                }

                is Result.Failure -> showError(it.ex)
            }
        }
    }

    private fun fetchData() {
        listingViewModel.getMovies(moviesSection)
    }

    private fun showError(ex: Throwable) {
        // TODO
    }

    companion object {
        const val TAG = "MoviesListingFragment"
    }

    interface MoviesListingInteractions {
        fun onMovieSelected(model: MovieUiModel, navigatorExtras: FragmentNavigator.Extras)
    }
}
