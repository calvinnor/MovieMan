package com.calvinnor.core.extensions

import java.text.SimpleDateFormat
import java.util.*

// Formats
private const val READABLE_DATE_FORMAT = "MMMM d, yyyy"

// Formatters
private val readableDateFormatter = SimpleDateFormat(READABLE_DATE_FORMAT, Locale.ENGLISH)

fun Date.toReadableDate(): String = readableDateFormatter.format(this)
