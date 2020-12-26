package com.artf.shoppinglistcompose.ui.view.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.model.AmbientSharedViewModel
import com.artf.shoppinglistcompose.ui.model.model.compose.MainMenuModel.screenStateState
import com.artf.shoppinglistcompose.ui.model.model.compose.MainMenuModel.showMenu
import com.artf.shoppinglistcompose.ui.model.state.ScreenState

@Composable
fun MainMenu() {
    val sharedViewModel = AmbientSharedViewModel.current

    IconButton(onClick = { showMenu.value = true }) {
        Icon(vectorResource(R.drawable.ic_baseline_more_vert_black_24))
    }
    if (showMenu.value.not()) {
        if (screenStateState.value == ScreenState.Empty) return
        screenStateState.value.let { sharedViewModel.pushBackStack(it) }
        screenStateState.value = ScreenState.Empty
        return
    }
    DropdownMenu(
        toggle = { showMenu.value = true },
        expanded = showMenu.value,
        onDismissRequest = { showMenu.value = false }
    ) {
        DropdownMenuItem(onClick = {
            screenStateState.value = ScreenState.CurrentShoppingList
            showMenu.value = false
        }) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                text = stringResource(id = R.string.menu_current_shopping_list),
                color = MaterialTheme.colors.onSurface,
                maxLines = 1
            )
        }
        DropdownMenuItem(onClick = {
            screenStateState.value = ScreenState.ArchivedShoppingList
            showMenu.value = false
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