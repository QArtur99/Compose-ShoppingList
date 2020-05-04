package com.artf.shoppinglistcompose.util

import android.content.res.Resources
import android.os.Handler
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.remember
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun <T> observer(data: LiveData<T>): T? {
    val result = state { data.value }
    val observer = remember { Observer<T> { result.value = it } }

    onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }
    return result.value
}

fun Long.getDateFormat(): String {
    val df = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    df.timeZone = TimeZone.getTimeZone("UTC")
    return df.format(Date(this))
}

fun Int.dpToPx(): Int = ((this * Resources.getSystem().displayMetrics.density).toInt())
fun Int.pxToDp(): Int = ((this / Resources.getSystem().displayMetrics.density).toInt())

/**
 * [avoidLagging] on old phones closing drawerContent is lagging to prevent it
 * let drawer execute closing animation then execute action onDrawer close
 */
fun avoidLagging(callback: () -> Unit) {
    Handler().postDelayed({
        callback()
    }, 400)
}

fun getCharSize(): Int = when (Resources.getSystem().displayMetrics.density) {
    in 3.5f..Float.MAX_VALUE -> 2
    in 2.5f..3.5f -> 3
    in 2f..2.5f -> 4
    in 1.5f..2f -> 5
    in 1f..1.5f -> 6
    else -> 7
}
