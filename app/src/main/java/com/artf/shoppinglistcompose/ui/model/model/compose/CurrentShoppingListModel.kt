package com.artf.shoppinglistcompose.ui.model.model.compose

import androidx.compose.material.DrawerValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.TextRange

object CurrentShoppingListModel {
    var drawerState = mutableStateOf(DrawerValue.Closed)
    var showDialogState = mutableStateOf(false)
    var shoppingListNameState = mutableStateOf("")
    var editTextFocusState = mutableStateOf(true)
    var editTextSelectionState = mutableStateOf(TextRange(0, 0))
}