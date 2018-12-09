package com.calvinnor.movie.details.ui

import android.os.Bundle
import com.calvinnor.core.extensions.ScaleType
import com.calvinnor.core.extensions.setImage
import com.calvinnor.core.ui.BaseActivity
import com.calvinnor.movie.R
import com.calvinnor.movie.commons.data.Movie
import com.calvinnor.movie.commons.data.remote.MovieWebService
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class MovieDetailsActivity : BaseActivity() {

    override val contentLayout = R.layout.activity_movie_details

    private val routineContext by lazy { CoroutineScope(parentJob + Dispatchers.Main) }
    private val parentJob by lazy { Job() }

    private val movieWebService: MovieWebService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        routineContext.launch(parentJob) {
            val movieData = withContext(Dispatchers.IO) { movieWebService.getMovie(movieId = "550").await() }
            setData(movieData)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        routineContext.coroutineContext.cancel()
    }

    private fun setData(movie: Movie) = with(movie) {
        tvTitle.text = title
        tvOverviewDesc.text = overview
        ivBackdrop.setImage(
            imageUrl = "https://image.tmdb.org/t/p/w500$backdropPath",
            scaleType = ScaleType.CENTER_CROP
        )

        ivPoster.setImage(
            imageUrl = "https://image.tmdb.org/t/p/w500$posterPath",
            scaleType = ScaleType.FIT_CENTER
        )
    }
}
