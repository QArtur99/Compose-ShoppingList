package com.artf.shopinglistcompose.ui.data

import androidx.compose.ambientOf

val ScreenBackStackAmbient = ambientOf<ScreenBackStack> {
    throw IllegalStateException("ScreenBackStack is not initialized")
}

val SharedViewModelAmbient = ambientOf<SharedViewModel> {
    throw IllegalStateException("SharedViewModel is not initialized")
}