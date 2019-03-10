package com.calvinnor.core.networking.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

/**
 * Converts to / from Date Format yyyy-MM-dd
 *
 * eg: 1970-01-03 is January 3rd, 1970
 */
class DateTimeAdapter {

    @ToJson
    fun toJson(date: Date): String = dateFormat.format(date)

    @FromJson
    fun fromJson(dateStr: String): Date = dateFormat.parse(dateStr)

    companion object {
        private const val DATE_TIME_FORMAT = "yyyy-MM-dd"
        private val dateFormat = SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH)
    }
}
