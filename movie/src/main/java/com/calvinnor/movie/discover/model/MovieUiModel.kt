package com.calvinnor.movie.discover.model

import com.calvinnor.data.movie.remote.api.MovieListing
import com.calvinnor.movie.commons.business.buildImagePath

data class MovieUiModel(
    val id: String,
    val title: String,
    val backdropImage: String,
    val releaseDate: String

) {

    constructor(movie: MovieListing) : this(
        id = movie.id.toString(),
        title = movie.title,
        backdropImage = buildImagePath(movie.backdropPath),
        releaseDate = movie.releaseDate
    )
}
