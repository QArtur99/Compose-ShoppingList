package com.artf.shoppinglistcompose.ui.model.model.compose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.artf.shoppinglistcompose.ui.model.state.ScreenState

object MainMenuModel {
    var showMenu = mutableStateOf(false)
    var screenStateState: MutableState<ScreenState> = mutableStateOf(ScreenState.Empty)
}
