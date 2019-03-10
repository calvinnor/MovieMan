package com.calvinnor.data.movie.remote.api

import com.squareup.moshi.Json

data class MovieListing(
    @Json(name = "id") val id: Int,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "title") val title: String
)
