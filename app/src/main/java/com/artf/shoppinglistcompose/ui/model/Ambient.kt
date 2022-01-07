package com.artf.shoppinglistcompose.ui.model


import androidx.compose.runtime.compositionLocalOf
import com.artf.shoppinglistcompose.ui.model.model.ScreenUi

val AmbientSharedViewModel = compositionLocalOf<SharedViewModel> {
    throw IllegalStateException("SharedViewModel is not initialized")
}

val AmbientScreenState = compositionLocalOf<ScreenUi> {
    throw IllegalStateException("SharedViewModel is not initialized")
}