package com.calvinnor.movie.details.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.palette.graphics.Palette
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.ScaleType
import com.calvinnor.core.extensions.observe
import com.calvinnor.core.extensions.setImage
import com.calvinnor.core.ui.BaseFragment
import com.calvinnor.core.utils.fadeColors
import com.calvinnor.movie.R
import com.calvinnor.movie.details.model.MovieDetailsUiModel
import com.calvinnor.movie.details.viewmodel.MovieDetailsViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment() {

    override val fragmentTag = TAG

    override val layout = R.layout.fragment_movie_details
    private val viewModel: MovieDetailsViewModel by viewModel()
    private val movieId by lazy {
        arguments.let {
            if (it == null) throw IllegalStateException("Need a movie ID!")
            else it.getString(KEY_MOVIE_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        fetchData()
    }

    private fun setupListeners() {
        viewModel.movieDetails.observe(this) {
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
        viewModel.getMovieDetails(movieId = movieId)
    }

    private fun showError(ex: Throwable) {
        // TODO
    }

    private fun showLoading(isLoading: Boolean) {
        tvOverviewTitle.isVisible = !isLoading
        cpbMovie.isVisible = isLoading
    }

    private fun setData(uiModel: MovieDetailsUiModel) = with(uiModel) {
        tvTitle.text = title
        tvOverviewDesc.text = description
        ivBackdrop.setImage(
            imageUrl = backdropImage,
            scaleType = ScaleType.CENTER_CROP,
            onSuccess = { it -> extractDarkColorAndFadeBackground(it.toBitmap()) }
        )

        ivPoster.setImage(
            imageUrl = posterImage,
            scaleType = ScaleType.FIT_CENTER
        )
    }

    private fun extractDarkColorAndFadeBackground(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            palette?.getDarkVibrantColor(ContextCompat.getColor(context!!, R.color.black_65))?.let {
                fadeColors(from = ContextCompat.getColor(context!!, R.color.black_65), to = it) {
                    nsvParent.setBackgroundColor(it)
                }
            }
        }
    }

    companion object {
        const val TAG = "MovieDetailsFragment"

        private const val KEY_MOVIE_ID = "movie_id"

        fun getBundle(movieId: String) = Bundle().apply {
            putString(KEY_MOVIE_ID, movieId)
        }
    }
}
