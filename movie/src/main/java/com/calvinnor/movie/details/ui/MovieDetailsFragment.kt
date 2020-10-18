package com.calvinnor.movie.details.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.text.scale
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.transition.TransitionListenerAdapter
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.*
import com.calvinnor.core.ui.BaseFragment
import com.calvinnor.core.utils.Theme
import com.calvinnor.core.utils.uiTheme
import com.calvinnor.movie.R
import com.calvinnor.movie.commons.util.getBackdropImageTransitionName
import com.calvinnor.movie.commons.util.getBackgroundTransitionName
import com.calvinnor.movie.details.di.MovieDetailsModule
import com.calvinnor.movie.details.model.MovieDetailsUiModel
import com.calvinnor.movie.details.viewmodel.MovieDetailsViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details) {

    override val fragmentTag = TAG

    private val viewModel: MovieDetailsViewModel by viewModel()
    private val navArgs: MovieDetailsFragmentArgs by navArgs()

    override fun loadDependencies() = MovieDetailsModule.load()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSharedElementTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPosterHeight()
        setupForInsets()
        setupInitialUi()
        fetchData()
    }

    private fun setupInitialUi() = navArgs.movieUiModel.let { uiModel ->
        val movieId = uiModel.id
        uiModel.backdropImage.let { backdropImageUrl ->
            if (backdropImageUrl.isNotEmpty()) ivBackdrop.run {
                transitionName = getBackdropImageTransitionName(movieId)
                setImage(
                    imageUrl = backdropImageUrl,
                    fadeIn = false,
                    scaleType = ScaleType.CENTER_CROP
                )
            }
        }

        clBackground.transitionName = getBackgroundTransitionName(movieId)
    }

    /* Avoid "Overview" title jumping while poster image loads */
    private fun setPosterHeight() = ivPoster.doOnPreDraw {
        it.setDimensions(newHeight = (it.measuredWidth * ASPECT_RATIO).roundToInt())
    }

    private fun setupForInsets() {
        nsvParent.setOnApplyWindowInsetsListener { v, insets ->
            vNavigationBarScrollSpace.updateLayoutParams {
                height = insets.systemWindowInsetBottom
            }
            return@setOnApplyWindowInsetsListener insets
        }

        nsvParent.requestApplyInsets()
    }

    private fun setupListeners() {
        observe(viewModel.movieDetails) {
            when (it) {
                is Result.Loading -> showLoading(true)

                is Result.Success -> {
                    showLoading(false)
                    setData(it.data)
                }

                is Result.Failure -> showError(it.ex)
            }
        }
    }

    private fun fetchData() {
        viewModel.getMovieDetails(movieId = navArgs.movieUiModel.id)
    }

    private fun setupSharedElementTransition() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
                .addListener(onTransitionEndListener())
    }

    private fun onTransitionEndListener() = object : TransitionListenerAdapter() {
        override fun onTransitionEnd(transition: Transition) {
            super.onTransitionEnd(transition)
            setupListeners()
        }
    }

    private fun showError(ex: Throwable) {
        // TODO
    }

    private fun showLoading(isLoading: Boolean) {
        cpbMovie.isVisible = isLoading
    }

    private fun setData(uiModel: MovieDetailsUiModel) = with(uiModel) {
        tvTitle.text = buildTitleWithReleaseDate(uiModel)
        tvOverviewDesc.text = description
        contextNonNull.getBitmapDrawable(backdropImage) { extractDarkColorAndCircularReveal(it) }

        ivPoster.setImage(
            imageUrl = posterImage,
            scaleType = ScaleType.FIT_CENTER
        )

        groupDetails.isVisible = true

        llOpenInTmdb.setOnClickListener { openMovieOnTmdb() }
    }

    private fun extractDarkColorAndCircularReveal(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            if (palette == null) return@generate
            val defaultColor = colorFrom(R.color.colorPrimaryDark)
            val vibrantColor = when (uiTheme) {
                Theme.NIGHT -> palette.getDarkVibrantColor(defaultColor)
                Theme.LIGHT -> palette.getLightMutedColor(defaultColor)
            }
            circularReveal(vibrantColor)
        }
    }

    private fun circularReveal(newColor: Int) {
        crNewBackground.setBackgroundColor(newColor)

        ViewAnimationUtils.createCircularReveal(
            crNewBackground,
            ivBackdrop.measuredWidth / 2,
            ivBackdrop.bottom,
            0f,
            nsvParent.measuredHeight.toFloat()
        )
            .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
            .start()
    }

    private fun buildTitleWithReleaseDate(uiModel: MovieDetailsUiModel) = buildSpannedString {
        // Title is bold
        bold { append(uiModel.title) }

        // If we don't have a Release Year, don't show the brackets either
        if (uiModel.releaseYear.isEmpty()) return@buildSpannedString

        // Space after the title
        append(" ")

        // Release year is secondary color, with 80% size
        scale(RELEASE_YEAR_RELATIVE_SIZE) {
            color(colorFrom(R.color.text_secondary)) {
                append(getString(R.string.movie_details_year, uiModel.releaseYear))
            }
        }
    }

    private fun openMovieOnTmdb() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.themoviedb.org/movie/${navArgs.movieUiModel.id}")
            )
        )
    }

    companion object {
        const val TAG = "MovieDetailsFragment"

        private const val ASPECT_RATIO = 1.5
        private const val RELEASE_YEAR_RELATIVE_SIZE = 0.8f
    }
}
