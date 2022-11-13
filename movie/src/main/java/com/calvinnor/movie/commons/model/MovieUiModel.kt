package com.calvinnor.movie.commons.model

import android.os.Parcelable
import com.calvinnor.core.extensions.toReadableDate
import com.calvinnor.core.pagination.PaginatedItem
import com.calvinnor.core.utils.emptyString
import com.calvinnor.data.movie.remote.api.MovieListing
import com.calvinnor.movie.commons.business.buildImagePath
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUiModel(
    val id: String,
    val title: String,
    val backdropImage: String,
    val releaseDate: String

) : PaginatedItem, Parcelable {

    constructor(movie: MovieListing) : this(
        id = movie.id.toString(),
        title = movie.title,
        backdropImage = buildImagePath(movie.backdropPath).orEmpty(),
        releaseDate = movie.releaseDate?.toReadableDate() ?: emptyString()
    )
}
