package com.calvinnor.data.movie.remote.api

import com.google.gson.annotations.SerializedName

data class MovieListingEnvelope(
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Long,
    @SerializedName("total_pages") val totalPages: Long,
    @SerializedName("results") val results: List<MovieListing>

)
