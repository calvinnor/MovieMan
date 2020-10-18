package com.calvinnor.core.extensions

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment

fun AppCompatActivity.closeKeyboard() {
    currentFocus?.run { closeKeyboard() }
}

fun Fragment.closeKeyboard() {
    view?.run { closeKeyboard() }
}

fun View.closeKeyboard() {
    getSystemService(context, InputMethodManager::class.java)
        ?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.focusAndOpenKeyboard() {
    this.requestFocus()
    getSystemService(context, InputMethodManager::class.java)
        ?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}
