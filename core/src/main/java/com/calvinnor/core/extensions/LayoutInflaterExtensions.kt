package com.calvinnor.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Inflates a layout with default parameters if not passed
 */
fun LayoutInflater.inflateDefault(
    @LayoutRes layoutRes: Int,
    viewParent: ViewGroup? = null,
    attachToRoot: Boolean = false
): View = inflate(layoutRes, viewParent, attachToRoot)
