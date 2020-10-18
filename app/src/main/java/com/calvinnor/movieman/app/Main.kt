package com.calvinnor.movieman.app

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.calvinnor.core.application.CoreApp
import com.calvinnor.core.utils.emptyString
import com.calvinnor.data.AppDb
import com.calvinnor.data.preferences.PREFS_APP_THEME
import com.calvinnor.data.preferences.themePreferenceToAppCompatTheme
import com.calvinnor.data.util.daoModule
import com.calvinnor.data.util.webServiceModule
import org.koin.android.ext.android.inject

/**
 * Android's Application class.
 * Use for 3rd party library init and other setup.
 */
class Main : CoreApp() {

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate() {
        super.onCreate()
        initialiseAppTheme()
    }

    private fun initialiseAppTheme() {
        val themeId = sharedPreferences.getString(PREFS_APP_THEME, emptyString()) ?: emptyString()
        AppCompatDelegate.setDefaultNightMode(themePreferenceToAppCompatTheme(themeId))
    }

    override fun getDataModules() = arrayOf(
        daoModule(AppDb.getDatabase(applicationContext)),
        webServiceModule
    )
}
