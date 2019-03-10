package com.calvinnor.data.movie.remote.api

import com.squareup.moshi.Json
import java.util.*

data class MovieR(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "release_date") val releaseDate: Date
)
