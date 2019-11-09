package com.calvinnor.core.networking.adapters

import com.calvinnor.core.utils.emptyString
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_TIME_FORMAT = "yyyy-MM-dd"
private val dateFormat = SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH)

/**
 * Converts to / from Date Format yyyy-MM-dd
 *
 * eg: 1970-01-03 is January 3rd, 1970
 */
class DateTimeAdapter {

    @ToJson
    fun toJson(date: Date?): String = dateToString(date)

    @FromJson
    fun fromJson(date: String): Date? = stringToDate(date)

    companion object {

        fun dateToString(date: Date?): String =
            if (date == null) emptyString() else dateFormat.format(date)

        fun stringToDate(date: String): Date? =
            if (date.isNotEmpty()) dateFormat.parse(date) else null
    }
}
