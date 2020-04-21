package com.artf.shopinglistcompose.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

enum class ShoppingListType { CURRENT, ARCHIVED }

fun getDateFormat(): SimpleDateFormat {
    val df = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    df.timeZone = TimeZone.getTimeZone("UTC")
    return df
}