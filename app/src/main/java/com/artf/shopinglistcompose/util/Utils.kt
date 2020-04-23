package com.artf.shopinglistcompose.util

import android.content.res.Resources
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

enum class ShoppingListType { CURRENT, ARCHIVED }

fun Long.getDateFormat(): String {
    val df = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    df.timeZone = TimeZone.getTimeZone("UTC")
    return df.format(Date(this))
}

fun Int.dpToPx(): Int = ((this * Resources.getSystem().displayMetrics.density).toInt())
fun Int.pxToDp(): Int = ((this / Resources.getSystem().displayMetrics.density).toInt())