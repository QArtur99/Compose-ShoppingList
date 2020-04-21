package com.artf.shopinglistcompose.ui.view.layout

import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.remember
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

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