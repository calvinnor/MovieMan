package com.calvinnor.data.movie.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.calvinnor.data.movie.remote.api.MovieR
import java.util.*

@Entity(tableName = "Movie")
data class MovieL(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val releaseDate: Date

) {

    constructor(movie: MovieR) : this(
        id = movie.id,
        title = movie.title,
        overview = movie.overview,
        backdropPath = movie.backdropPath,
        posterPath = movie.posterPath,
        releaseDate = movie.releaseDate
    )
}
