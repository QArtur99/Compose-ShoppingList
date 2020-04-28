package com.artf.shoppinglistcompose.ui.data.model.compose

import androidx.compose.Model
import com.artf.shoppinglistcompose.ui.data.status.Screen

@Model
object MainMenuModel {
    var showMenu = false
    var screenState: Screen? = null
}