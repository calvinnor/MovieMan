package com.calvinnor.movie.details.model

import com.calvinnor.data.movie.local.entities.MovieL
import com.calvinnor.data.movie.remote.api.MovieR
import com.calvinnor.movie.commons.business.buildImagePath

data class MovieDetailsUiModel(
    val title: String,
    val description: String,
    val backdropImage: String,
    val posterImage: String

) {

    constructor(movie: MovieR) : this(
        title = movie.title,
        description = movie.overview,
        backdropImage = buildImagePath(movie.backdropPath),
        posterImage = buildImagePath(movie.posterPath)
    )

    constructor(movie: MovieL) : this(
        title = movie.title,
        description = movie.overview,
        backdropImage = buildImagePath(movie.backdropPath),
        posterImage = buildImagePath(movie.posterPath)
    )
}
