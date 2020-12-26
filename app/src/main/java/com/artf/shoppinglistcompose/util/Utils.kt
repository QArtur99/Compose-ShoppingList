package com.artf.shoppinglistcompose.util

import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.getDateFormat(): String {
    val df = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    df.timeZone = TimeZone.getTimeZone("UTC")
    return df.format(Date(this))
}

fun Int.dpToPx(): Float = ((this * Resources.getSystem().displayMetrics.density))
fun Int.pxToDp(): Float = ((this / Resources.getSystem().displayMetrics.density))

/**
 * [avoidLagging] on old phones closing drawerContent is lagging to prevent it
 * let drawer execute closing animation then execute action onDrawer close
 */
fun avoidLagging(callback: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        callback()
    }, 400)
}

