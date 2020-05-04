package com.artf.shoppinglistcompose.ui.view.menu

import androidx.compose.Composable
import androidx.ui.core.DropdownPopup
import androidx.ui.core.Modifier
import androidx.ui.core.PopupProperties
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.width
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.material.TextButton
import androidx.ui.res.stringResource
import androidx.ui.res.vectorResource
import androidx.ui.unit.Dp
import androidx.ui.unit.IntPx
import androidx.ui.unit.IntPxPosition
import androidx.ui.unit.dp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.model.SharedViewModelAmbient
import com.artf.shoppinglistcompose.ui.model.model.compose.MainMenuModel.screenStateState
import com.artf.shoppinglistcompose.ui.model.model.compose.MainMenuModel.showMenu
import com.artf.shoppinglistcompose.ui.model.state.ScreenState
import com.artf.shoppinglistcompose.util.dpToPx
import com.artf.shoppinglistcompose.util.getCharSize

@Composable
fun MainMenu() {
    val sharedViewModel = SharedViewModelAmbient.current

    IconButton(onClick = { showMenu = true }) {
        Icon(vectorResource(R.drawable.ic_baseline_more_vert_black_24))
    }
    if (showMenu.not()) {
        if (screenStateState == null) return
        screenStateState?.let { sharedViewModel.pushBackStack(it) }
        screenStateState = null
        return
    }
    DropdownPopup(
        offset = IntPxPosition(IntPx(0.dpToPx()), IntPx((-48 - 16).dpToPx())),
        popupProperties = PopupProperties(true) { showMenu = false }
    ) {
        Row(Modifier.padding(16.dp, 16.dp, 4.dp, 16.dp)) {
            Surface(
                Modifier.width((getMenuSize())),
                shape = RoundedCornerShape(4.dp),
                elevation = 8.dp
            ) {
                Column {
                    TextButton(onClick = {
                        screenStateState = ScreenState.CurrentShoppingList
                        showMenu = false
                    }) {
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            text = stringResource(id = R.string.menu_current_shopping_list),
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 1
                        )
                    }
                    TextButton(onClick = {
                        screenStateState = ScreenState.ArchivedShoppingList
                        showMenu = false
                    }) {
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            text = stringResource(id = R.string.menu_archived_shopping_list),
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun getMenuSize(): Dp {
    val spaceForChar = getCharSize()
    var result = 0
    mutableListOf<String>().apply {
        add(stringResource(id = R.string.menu_current_shopping_list))
        add(stringResource(id = R.string.menu_archived_shopping_list))
    }.map { if (it.length > result) result = it.length }
    return ((result * spaceForChar).dpToPx() + 24 + 12).dp
}