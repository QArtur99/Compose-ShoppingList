package com.artf.shoppinglistcompose.ui.data

import androidx.compose.ambientOf
import com.artf.shoppinglistcompose.ui.data.model.ScreenUi

val SharedViewModelAmbient = ambientOf<SharedViewModel> {
    throw IllegalStateException("SharedViewModel is not initialized")
}

val ScreenStateAmbient = ambientOf<ScreenUi> {
    throw IllegalStateException("SharedViewModel is not initialized")
}