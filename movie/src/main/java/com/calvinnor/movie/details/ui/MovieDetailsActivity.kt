package com.calvinnor.movie.details.ui

import android.os.Bundle
import androidx.core.view.isVisible
import com.calvinnor.core.domain.Result
import com.calvinnor.core.extensions.ScaleType
import com.calvinnor.core.extensions.observe
import com.calvinnor.core.extensions.setImage
import com.calvinnor.core.ui.BaseActivity
import com.calvinnor.movie.R
import com.calvinnor.movie.details.model.MovieDetailsUiModel
import com.calvinnor.movie.details.viewmodel.MovieDetailsViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : BaseActivity() {

    override val contentLayout = R.layout.activity_movie_details

    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        viewModel.getMovieDetails(movieId = "550")
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
            scaleType = ScaleType.CENTER_CROP
        )

        ivPoster.setImage(
            imageUrl = posterImage,
            scaleType = ScaleType.FIT_CENTER
        )
    }
}
