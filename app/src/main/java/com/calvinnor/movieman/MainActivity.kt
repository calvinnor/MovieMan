package com.calvinnor.movieman

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calvinnor.movieman.core.extensions.ScaleType
import com.calvinnor.movieman.core.extensions.setImage
import com.calvinnor.movieman.data.Movie
import com.calvinnor.movieman.data.remote.MovieWebService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val routineContext by lazy { CoroutineScope(Dispatchers.Main) }

    private val movieWebService: MovieWebService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        routineContext.launch {
            val movieData = withContext(Dispatchers.IO) { movieWebService.getMovie(movieId = "550").await() }
            setData(movieData)
        }
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
