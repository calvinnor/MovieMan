package com.calvinnor.data.movie.remote.api

import com.google.gson.annotations.SerializedName

data class MovieR(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("poster_path") val posterPath: String
)
