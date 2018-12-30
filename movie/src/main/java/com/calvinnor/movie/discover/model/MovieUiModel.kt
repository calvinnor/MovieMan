package com.calvinnor.movie.discover.model

import com.calvinnor.movie.commons.data.MovieListing

data class MovieUiModel(
    val id: String,
    val title: String,
    val backdropImage: String,
    val releaseDate: String

) {

    constructor(movie: MovieListing) : this(
        id = movie.id.toString(),
        title = movie.title,
        backdropImage = "https://image.tmdb.org/t/p/w500${movie.backdropPath}",
        releaseDate = movie.releaseDate
    )
}
