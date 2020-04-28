package com.artf.shoppinglistcompose.ui.data

import androidx.compose.ambientOf
import com.artf.shoppinglistcompose.ui.data.model.ScreenState

val SharedViewModelAmbient = ambientOf<SharedViewModel> {
    throw IllegalStateException("SharedViewModel is not initialized")
}

val ScreenStateAmbient = ambientOf<ScreenState> {
    throw IllegalStateException("SharedViewModel is not initialized")
}