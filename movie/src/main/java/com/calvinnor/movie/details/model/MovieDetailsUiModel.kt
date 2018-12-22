package com.calvinnor.movie.details.model

import com.calvinnor.movie.commons.data.Movie

data class MovieDetailsUiModel(
    val title: String,
    val description: String,
    val backdropImage: String,
    val posterImage: String

) {

    constructor(movie: Movie) : this(
        title = movie.title,
        description = movie.overview,
        backdropImage = "https://image.tmdb.org/t/p/w500${movie.backdropPath}",
        posterImage = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
    )
}
