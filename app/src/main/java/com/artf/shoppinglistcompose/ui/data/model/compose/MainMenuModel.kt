package com.artf.shoppinglistcompose.ui.data.model.compose

import androidx.compose.Model
import com.artf.shoppinglistcompose.ui.data.status.ScreenStatus

@Model
object MainMenuModel {
    var showMenu = false
    var screenStatusState: ScreenStatus? = null
}