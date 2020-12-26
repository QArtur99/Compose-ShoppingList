package com.artf.shoppinglistcompose.ui.model


import androidx.compose.runtime.ambientOf
import com.artf.shoppinglistcompose.ui.model.model.ScreenUi

val AmbientSharedViewModel = ambientOf<SharedViewModel> {
    throw IllegalStateException("SharedViewModel is not initialized")
}

val AmbientScreenState = ambientOf<ScreenUi> {
    throw IllegalStateException("SharedViewModel is not initialized")
}