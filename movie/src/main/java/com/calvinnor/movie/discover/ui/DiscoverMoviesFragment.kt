package com.calvinnor.movie.discover.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.calvinnor.core.ui.BaseFragment
import com.calvinnor.core.utils.showToast
import com.calvinnor.movie.R
import com.calvinnor.movie.commons.model.MovieUiModel
import com.calvinnor.movie.commons.model.MoviesSection
import com.calvinnor.movie.databinding.FragmentDiscoverMoviesBinding
import com.calvinnor.movie.details.ui.MovieDetailsFragmentArgs
import com.calvinnor.movie.listing.ui.MoviesListingFragment
import com.calvinnor.movie.listing.ui.MoviesListingFragmentArgs

class DiscoverMoviesFragment : BaseFragment<FragmentDiscoverMoviesBinding>(),
    MoviesSectionBottomDialogFragment.Companion.NavigationInteractions,
    MoviesListingFragment.MoviesListingInteractions {

    override val fragmentTag = TAG

    private var selectedSection: MoviesSection = MoviesSection.POPULAR

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDiscoverMoviesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let { restoreSelectedSection(it) }
        setupToolbar()
        setupBottomAppBar()
    }

    private fun setupToolbar() = viewBinding.tvToolbarTitle.setText(
        when (selectedSection) {
            MoviesSection.POPULAR -> R.string.bottom_nav_popular
            MoviesSection.NOW_PLAYING -> R.string.bottom_nav_now_playing
            MoviesSection.TOP_RATED -> R.string.bottom_nav_top_rated
            MoviesSection.UPCOMING -> R.string.bottom_nav_upcoming
        }
    )

    private fun setupBottomAppBar() = viewBinding.bottomAppBar.run {
        replaceMenu(R.menu.menu_discover)
        setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_search -> {
                    navigateToSearch(); true
                }
                R.id.menu_settings -> {
                    findNavController().navigate(R.id.navigateToSettings); true
                }
                R.id.menu_about -> {
                    // TODO(calvinnor): Pick up from BuildConfig
                    showToast("MovieMan 1.0"); true
                }
                else -> false
            }
        }

        setNavigationOnClickListener { showBottomNavigationOptions() }
    }

    private fun navigateToSearch() {
        findNavController().navigate(R.id.navigateToSearch)
    }

    private fun showBottomNavigationOptions() {
        MoviesSectionBottomDialogFragment.newInstance(selectedSection)
            .show(childFragmentManager, MoviesSectionBottomDialogFragment.TAG)
    }

    private fun restoreSelectedSection(savedInstanceState: Bundle) {
        selectedSection = savedInstanceState.getSerializable(SAVE_SELECTED_SECTION) as MoviesSection
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVE_SELECTED_SECTION, selectedSection)
    }

    override fun onNavigationItemPicked(moviesSection: MoviesSection) {
        selectedSection = moviesSection
        setupToolbar()

        viewBinding.fcvSection.findNavController()
            .navigate(
                R.id.navigateToMoviesListing,
                MoviesListingFragmentArgs(moviesSection).toBundle()
            )
    }

    companion object {
        const val TAG = "DiscoverMoviesFragment"

        private const val SAVE_SELECTED_SECTION = "save_selected_section"
    }

    override fun onMovieSelected(model: MovieUiModel, navigatorExtras: FragmentNavigator.Extras) {
        findNavController().navigate(
            R.id.navigateFromHomeToMovieDetails,
            MovieDetailsFragmentArgs(model).toBundle(),
            null,
            navigatorExtras
        )
    }
}
