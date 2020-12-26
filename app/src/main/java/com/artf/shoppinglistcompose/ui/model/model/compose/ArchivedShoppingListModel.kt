package com.artf.shoppinglistcompose.ui.model.model.compose

import androidx.compose.material.DrawerValue
import androidx.compose.runtime.mutableStateOf

object ArchivedShoppingListModel {
    var drawerState = mutableStateOf(DrawerValue.Closed)
}