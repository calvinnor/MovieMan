package com.calvinnor.data.movie.remote.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListingEnvelope(
    @Json(name = "page") val page: Int,
    @Json(name = "total_results") val totalResults: Long,
    @Json(name = "total_pages") val totalPages: Long,
    @Json(name = "results") val results: List<MovieListing>
)
