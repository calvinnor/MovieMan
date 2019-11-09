package com.calvinnor.core.extensions

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
