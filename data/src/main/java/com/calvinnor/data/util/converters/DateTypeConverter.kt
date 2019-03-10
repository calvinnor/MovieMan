package com.calvinnor.data.util.converters

import androidx.room.TypeConverter
import com.calvinnor.core.networking.adapters.DateTimeAdapter
import java.util.*

/**
 * Date <-> String TypeConverter for Room
 *
 * For now, delegates to the Network adapter.
 */
class DateTypeConverter {

    @TypeConverter
    fun dateToString(date: Date): String = DateTimeAdapter.dateToString(date)

    @TypeConverter
    fun stringToDate(date: String): Date = DateTimeAdapter.stringToDate(date)

}
