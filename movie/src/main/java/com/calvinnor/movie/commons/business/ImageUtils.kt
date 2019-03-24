package com.calvinnor.movie.commons.business

/**
 * Builds the full path for TMDB movies.
 *
 * TODO: Figure out the actual dimensions based on device.
 */
fun buildImagePath(relativePath: String?) =
    if (relativePath.isNullOrEmpty()) null
    else "https://image.tmdb.org/t/p/w780$relativePath"

