package com.calvinnor.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible

/**
 * Set the height / width of a View via Layout Params.
 */
fun View.setDimensions(newWidth: Int = measuredWidth, newHeight: Int = measuredHeight) {
    val viewLayoutParams = layoutParams
    layoutParams = viewLayoutParams.apply {
        width = newWidth; height = newHeight
    }
}

fun TextView.setTextOrGone(newText: String) {
    isVisible = newText.isNotEmpty()
    text = newText
}

/**
 * Return a LayoutInflater built from View.
 */
fun View.layoutInflater(): LayoutInflater = LayoutInflater.from(context)
