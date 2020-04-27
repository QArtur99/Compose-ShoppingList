package com.artf.shopinglistcompose.ui.view.layout.currentList

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.remember
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.CornerSize
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.DrawerState
import androidx.ui.material.FloatingActionButton
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.material.TopAppBar
import androidx.ui.material.contentColorFor
import androidx.ui.res.stringResource
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.artf.shopinglistcompose.R
import com.artf.shopinglistcompose.ui.data.Screen
import com.artf.shopinglistcompose.ui.data.SharedViewModelAmbient
import com.artf.shopinglistcompose.ui.view.layout.EmptyScreen
import com.artf.shopinglistcompose.ui.view.menu.AppDrawer
import com.artf.shopinglistcompose.ui.view.menu.MainMenu
import com.artf.shopinglistcompose.util.ShoppingListType
import com.artf.shopinglistcompose.util.observer

@Composable
fun ShoppingListCurrentScreen(
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    val showDialog = state { false }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = Screen.ShoppingListCurrent,
                closeDrawer = { scaffoldState.drawerState = DrawerState.Closed }
            )
        },
        topAppBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = DrawerState.Opened }) {
                        Icon(vectorResource(R.drawable.ic_baseline_menu_24))
                    }
                },
                actions = { MainMenu() }
            )
        },
        bodyContent = {
            ScreenBody()
            ShoppingListDialog(showDialog)
        },
        floatingActionButton = { Fab(showDialog) }
    )
}

@Composable
private fun ScreenBody() {
    val sharedViewModelAmbient = SharedViewModelAmbient.current
    sharedViewModelAmbient.setShoppingListType(ShoppingListType.CURRENT)
    val postList = observer(sharedViewModelAmbient.shoppingLists)
    if (postList == null || postList.isEmpty()) {
        EmptyScreen(
            R.string.empty_view_shopping_list_title,
            R.string.empty_view_shopping_list_subtitle
        )
        return
    }
    VerticalScroller {
        Column(Modifier.fillMaxWidth().padding(8.dp, 8.dp, 8.dp, 96.dp)) {
            postList.forEach { post -> ShoppingListCurrentItem(sharedViewModelAmbient, post) }
        }
    }
}

@Composable
private fun Fab(showDialog: MutableState<Boolean>) {
    FloatingActionButton(
        onClick = { showDialog.value = true },
        modifier = Modifier,
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = contentColorFor(MaterialTheme.colors.onSecondary),
        elevation = 6.dp,
        icon = { Icon(vectorResource(R.drawable.ic_add_black_24dp)) }
    )
}