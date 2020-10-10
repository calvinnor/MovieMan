package com.calvinnor.core.utils

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment

val Fragment.uiTheme: Theme
    get() = uiTheme(resources)

val Activity.uiTheme: Theme
    get() = uiTheme(resources)

fun Theme.invert() = when (this) {
    Theme.LIGHT -> Theme.NIGHT
    Theme.NIGHT -> Theme.LIGHT
}

/**
 * Returns the MODE to be used with AppCompatDelegate.
 */
val Theme.appCompatMode: Int
    get() = when (this) {
        Theme.NIGHT -> MODE_NIGHT_YES
        Theme.LIGHT -> MODE_NIGHT_NO
    }

private fun uiTheme(resources: Resources?): Theme =
    when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
        Configuration.UI_MODE_NIGHT_YES -> Theme.NIGHT
        Configuration.UI_MODE_NIGHT_NO -> Theme.LIGHT

        // Default to Light mode
        else -> Theme.LIGHT
    }
