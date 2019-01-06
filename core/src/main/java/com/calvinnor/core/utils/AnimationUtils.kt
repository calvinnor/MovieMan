@file: JvmName("AnimationUtils")

package com.calvinnor.core.utils

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator

/**
 * Fades between two provided colors
 *
 * @param from: The initial color
 * @param to: The final color
 * @param update: Function which will receive color updates.
 */
fun fadeColors(from: Int, to: Int, update: (color: Int) -> Unit) {
    if (from == to) return
    ValueAnimator.ofInt(from, to).apply {
        setEvaluator(ArgbEvaluator())
        addUpdateListener { animation -> update(animation.animatedValue as Int) }

    }.start()
}
