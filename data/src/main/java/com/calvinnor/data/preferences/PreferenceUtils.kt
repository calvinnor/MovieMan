package com.calvinnor.data.preferences

import androidx.appcompat.app.AppCompatDelegate

@AppCompatDelegate.NightMode
fun themePreferenceToAppCompatTheme(themeId: String) = when (themeId) {
    PREFS_APP_THEME_VALUE_AUTO -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    PREFS_APP_THEME_VALUE_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
    PREFS_APP_THEME_VALUE_NIGHT -> AppCompatDelegate.MODE_NIGHT_YES
    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
}
