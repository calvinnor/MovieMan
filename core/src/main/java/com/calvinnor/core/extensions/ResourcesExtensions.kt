package com.calvinnor.core.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Context.colorFrom(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun Fragment.colorFrom(@ColorRes colorRes: Int) =
    ContextCompat.getColor(context as Context, colorRes)
