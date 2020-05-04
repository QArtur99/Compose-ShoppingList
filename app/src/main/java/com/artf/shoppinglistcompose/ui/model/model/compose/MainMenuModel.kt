package com.artf.shoppinglistcompose.ui.model.model.compose

import androidx.compose.Model
import com.artf.shoppinglistcompose.ui.model.state.ScreenState

@Model
object MainMenuModel {
    var showMenu = false
    var screenStateState: ScreenState? = null
}