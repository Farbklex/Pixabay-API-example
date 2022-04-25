package de.hypar.hoffman_pixa.util

import android.content.Context


/**
 * Convert pixel to dp
 */
fun Int.dpToPx(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}
