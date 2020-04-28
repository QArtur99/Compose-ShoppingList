package com.artf.shoppinglistcompose.ui.data.model.compose

import androidx.compose.Model
import androidx.ui.text.TextRange

@Model
object CurrentProductListModel {
    var showDialogState = false
    var productNameState = ""
    var editTextProductNameFocusState = true
    var editTextProductNameSelectionState = TextRange(0, 0)
    var productQuantityState = ""
    var editTextProductQuantityFocusState = true
    var editTextProductQuantitySelectionState = TextRange(0, 0)
}