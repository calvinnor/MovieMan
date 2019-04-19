package com.calvinnor.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.calvinnor.data.movie.local.dao.MovieDao
import com.calvinnor.data.movie.local.entities.MovieL
import com.calvinnor.data.util.converters.DateTypeConverter

@Database(
    entities = [MovieL::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDb : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "AppDatabase"

        private var sInstance: AppDb? = null

        @JvmStatic
        fun getDatabase(context: Context): AppDb {
            if (sInstance == null) {
                synchronized(this) {
                    if (sInstance == null)
                        sInstance = buildDatabase(context, AppDb::class.java, DATABASE_NAME).build()
                }
            }

            return sInstance as AppDb
        }

        private fun <T : RoomDatabase> buildDatabase(
            context: Context,
            dbClass: Class<T>,
            databaseName: String,
            inMemory: Boolean = false

        ): Builder<T> {

            // In Memory
            return if (inMemory) Room.inMemoryDatabaseBuilder(context, dbClass)

            // Persistent
            else Room.databaseBuilder(context, dbClass, databaseName)
        }
    }

    // Movie
    abstract fun movieDao(): MovieDao

}
