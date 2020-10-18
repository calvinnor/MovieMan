package com.calvinnor.settings.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.core.text.color
import androidx.core.text.scale
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.calvinnor.core.extensions.colorFrom
import com.calvinnor.core.extensions.drawableFrom
import com.calvinnor.core.utils.emptyString
import com.calvinnor.data.preferences.*
import com.calvinnor.settings.R
import org.koin.android.ext.android.inject

/**
 * Initialises app preferences using AndroidX Preferences.
 */
class SettingsPreferencesFragment : PreferenceFragmentCompat() {

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val context = preferenceManager.context
        preferenceScreen = preferenceManager.createPreferenceScreen(context).apply {
            addPreference(buildAppThemePreference(context))
        }
    }

    private fun buildAppThemePreference(context: Context) = ListPreference(context).apply {
        key = PREFS_APP_THEME
        title = resources.getString(R.string.pref_theme_title)
        summary = resources.getString(R.string.pref_theme_summary)
        icon = context.drawableFrom(R.drawable.ic_preferences_theme)
        entries = getAppThemeEntries()
        value = getThemeValue()
        entryValues = getAppThemeEntryValues()
        negativeButtonText = null
        setOnPreferenceChangeListener { _, newValue ->
            saveAppTheme(newValue.toString()); true
        }
    }

    private fun saveAppTheme(newValue: String) {
        sharedPreferences.edit { putString(PREFS_APP_THEME, newValue) }

        val appCompatTheme = themePreferenceToAppCompatTheme(newValue)
        AppCompatDelegate.setDefaultNightMode(appCompatTheme)
    }

    private fun getAppThemeEntries() = arrayOf(
        buildTitleWithHintEntry(R.string.pref_theme_auto_value, R.string.pref_theme_auto_summary),
        buildTitleWithHintEntry(R.string.pref_theme_light_value, R.string.pref_theme_light_summary),
        buildTitleWithHintEntry(R.string.pref_theme_night_value, R.string.pref_theme_night_summary),
    )

    private fun getAppThemeEntryValues() = arrayOf(
        PREFS_APP_THEME_VALUE_AUTO, PREFS_APP_THEME_VALUE_LIGHT, PREFS_APP_THEME_VALUE_NIGHT
    )

    private fun getThemeValue(): String? =
        sharedPreferences.getString(PREFS_APP_THEME, emptyString()).let {
            return if (it.isNullOrEmpty()) PREFS_APP_THEME_VALUE_AUTO
            else it
        }

    private fun buildTitleWithHintEntry(@StringRes titleId: Int, @StringRes hintId: Int) =
        SpannableStringBuilder().run {
            append(getString(titleId))
            append("\n")
            scale(0.75f) {
                color(colorFrom(R.color.text_secondary)) {
                    append(getString(hintId))
                }
            }
        }
}
