package com.calvinnor.movie.commons.model

import com.calvinnor.core.extensions.toReadableDate
import com.calvinnor.core.pagination.PaginatedItem
import com.calvinnor.data.movie.remote.api.MovieListing
import com.calvinnor.movie.commons.business.buildImagePath

data class MovieUiModel(
    val id: String,
    val title: String,
    val backdropImage: String,
    val releaseDate: String

) : PaginatedItem {

    constructor(movie: MovieListing) : this(
        id = movie.id.toString(),
        title = movie.title,
        backdropImage = buildImagePath(movie.backdropPath).orEmpty(),
        releaseDate = movie.releaseDate.toReadableDate()
    )
}
