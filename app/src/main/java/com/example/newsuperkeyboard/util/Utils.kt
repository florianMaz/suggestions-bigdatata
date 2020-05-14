package com.example.newsuperkeyboard.util

import android.content.Context
import android.util.TypedValue

fun convertDpToPx(context: Context, valueInDp: Float): Float {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics)
}

fun getIdFromString(s: String): String? {
    return when {
        s.contains("burger") -> { "10907" }
        s.contains("fajitas") -> { "5110" }
        s.contains("sushi") -> { "5473" }
        else -> null

    }
}