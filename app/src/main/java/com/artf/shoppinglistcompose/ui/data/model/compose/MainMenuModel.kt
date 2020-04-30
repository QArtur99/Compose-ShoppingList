package com.artf.shoppinglistcompose.ui.data.model.compose

import androidx.compose.Model
import com.artf.shoppinglistcompose.ui.data.state.ScreenState

@Model
object MainMenuModel {
    var showMenu = false
    var screenStateState: ScreenState? = null
}