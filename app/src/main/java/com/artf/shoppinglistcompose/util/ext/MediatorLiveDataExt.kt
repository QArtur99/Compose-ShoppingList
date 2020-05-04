package com.artf.shoppinglistcompose.util.ext

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, S> MediatorLiveData<T>.addSourceInvoke(
    @NonNull source: LiveData<S>,
    @NonNull callback: (it: S?) -> Unit
) {
    addSource(source) {
        callback(it)
        value = value
    }
}