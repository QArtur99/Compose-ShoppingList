package com.artf.shoppinglistcompose.ui.data.status

sealed class ResultState<out R> {

    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val exception: Exception) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()

    val succeeded: Boolean
        get() = this is Success<*> && data != null

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}
