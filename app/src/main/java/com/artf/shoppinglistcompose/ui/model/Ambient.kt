package com.artf.shoppinglistcompose.ui.model

import androidx.compose.ambientOf
import com.artf.shoppinglistcompose.ui.model.model.ScreenUi

val SharedViewModelAmbient = ambientOf<SharedViewModel> {
    throw IllegalStateException("SharedViewModel is not initialized")
}

val ScreenStateAmbient = ambientOf<ScreenUi> {
    throw IllegalStateException("SharedViewModel is not initialized")
}