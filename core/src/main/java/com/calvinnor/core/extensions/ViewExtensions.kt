package com.calvinnor.core.extensions

import android.view.View

/**
 * Set the height / width of a View via Layout Params.
 */
fun View.setDimensions(newWidth: Int = measuredWidth, newHeight: Int = measuredHeight) {
    val viewLayoutParams = layoutParams
    layoutParams = viewLayoutParams.apply {
        width = newWidth; height = newHeight
    }
}
