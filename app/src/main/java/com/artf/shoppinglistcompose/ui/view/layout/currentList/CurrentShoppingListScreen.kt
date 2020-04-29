package com.artf.shoppinglistcompose.ui.view.layout.currentList

import android.util.Log
import androidx.compose.Composable
import androidx.compose.remember
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
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.data.status.ScreenStatus
import com.artf.shoppinglistcompose.ui.data.ScreenStateAmbient
import com.artf.shoppinglistcompose.ui.data.SharedViewModelAmbient
import com.artf.shoppinglistcompose.ui.data.model.compose.CurrentShoppingListModel
import com.artf.shoppinglistcompose.ui.data.model.compose.CurrentShoppingListModel.showDialogState
import com.artf.shoppinglistcompose.ui.view.layout.EmptyScreen
import com.artf.shoppinglistcompose.ui.view.menu.AppDrawer
import com.artf.shoppinglistcompose.ui.view.menu.MainMenu

@Composable
fun ShoppingListCurrentScreen(
    scaffoldState: ScaffoldState = remember {
        ScaffoldState().apply { drawerState = CurrentShoppingListModel.drawerState }
    }
) {
    Log.e("TAG", "ShoppingListCurrentScreen")
    CurrentShoppingListModel.drawerState = scaffoldState.drawerState

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreenStatus = ScreenStatus.CurrentShoppingList,
                closeDrawer = {
                    scaffoldState.drawerState = DrawerState.Closed
                    CurrentShoppingListModel.drawerState = scaffoldState.drawerState
                }
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
            CreateShoppingListDialog()
        },
        floatingActionButton = { Fab() }
    )
}

@Composable
private fun ScreenBody() {
    val sharedViewModel = SharedViewModelAmbient.current
    val postList = ScreenStateAmbient.current.shoppingListsUi
    val isEmpty = ScreenStateAmbient.current.isShoppingListsEmpty

    if (isEmpty == true) {
        EmptyScreen(
            R.string.empty_view_shopping_list_title,
            R.string.empty_view_shopping_list_subtitle
        )
        return
    }
    VerticalScroller {
        Column(Modifier.fillMaxWidth().padding(8.dp, 8.dp, 8.dp, 96.dp)) {
            postList?.forEach { post -> ShoppingListCurrentItem(sharedViewModel, post) }
        }
    }
}

@Composable
private fun Fab() {
    FloatingActionButton(
        onClick = { showDialogState = true },
        modifier = Modifier,
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = contentColorFor(MaterialTheme.colors.onSecondary),
        elevation = 6.dp,
        icon = { Icon(vectorResource(R.drawable.ic_add_black_24dp)) }
    )
}