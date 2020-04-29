package com.artf.shoppinglistcompose.util.ext

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

@MainThread
fun <X, Y> mapNonNull(
    source: LiveData<X>,
    mapFunction: (it: X) -> Y?
): LiveData<Y> {
    val result = MediatorLiveData<Y>()
    result.addSource(source) { x ->
        x?.let {
            mapFunction(x)?.let {
                result.value = it
            }
        }
    }
    return result
}