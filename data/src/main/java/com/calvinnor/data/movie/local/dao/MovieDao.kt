package com.calvinnor.data.movie.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.calvinnor.data.movie.local.entities.MovieL
import com.calvinnor.data.util.BaseDao

@Dao
abstract class MovieDao : BaseDao<MovieL> {

    @Query("SELECT * FROM Movie WHERE id = :movieId")
    abstract fun getMovieDetails(movieId: String): MovieL?

}
