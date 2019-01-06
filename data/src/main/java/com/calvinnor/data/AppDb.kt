package com.calvinnor.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.calvinnor.data.movie.local.dao.MovieDao
import com.calvinnor.data.movie.local.entities.MovieL

@Database(
    entities = [MovieL::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "AppDatabase"

        private var sInstance: AppDb? = null

        @JvmStatic
        fun getDatabase(context: Context): AppDb {
            if (sInstance == null) {
                synchronized(this) {
                    if (sInstance == null)
                        sInstance = Room.databaseBuilder(
                            context,
                            AppDb::class.java,
                            DATABASE_NAME
                        ).build()
                }
            }

            return sInstance as AppDb
        }
    }

    // Movie
    abstract fun movieDao(): MovieDao

}
