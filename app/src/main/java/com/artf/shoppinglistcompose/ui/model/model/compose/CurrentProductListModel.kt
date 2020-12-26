package com.artf.shoppinglistcompose.ui.model.model.compose

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.TextRange

object CurrentProductListModel {
    var showDialogState = mutableStateOf(false)
    var productNameState = mutableStateOf("")
    var editTextProductNameFocusState = mutableStateOf(true)
    var editTextProductNameSelectionState = mutableStateOf(TextRange(0, 0))
    var productQuantityState = mutableStateOf("")
    var editTextProductQuantityFocusState = mutableStateOf(true)
    var editTextProductQuantitySelectionState = mutableStateOf(TextRange(0, 0))
}