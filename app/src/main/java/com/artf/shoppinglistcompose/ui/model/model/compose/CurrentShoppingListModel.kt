package com.artf.shoppinglistcompose.ui.model.model.compose

import androidx.compose.Model
import androidx.ui.material.DrawerState
import androidx.ui.text.TextRange

@Model
object CurrentShoppingListModel {
    var drawerState = DrawerState.Closed
    var showDialogState = false
    var shoppingListNameState = ""
    var editTextFocusState = true
    var editTextSelectionState = TextRange(0, 0)
}