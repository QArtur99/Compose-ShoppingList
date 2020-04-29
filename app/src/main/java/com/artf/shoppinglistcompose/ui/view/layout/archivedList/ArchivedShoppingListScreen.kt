package com.artf.shoppinglistcompose.ui.view.layout.archivedList

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.*
import androidx.ui.res.stringResource
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.artf.data.status.ResultStatus
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.data.ScreenStateAmbient
import com.artf.shoppinglistcompose.ui.data.SharedViewModelAmbient
import com.artf.shoppinglistcompose.ui.data.model.ShoppingListUi
import com.artf.shoppinglistcompose.ui.data.model.compose.ArchivedShoppingListModel
import com.artf.shoppinglistcompose.ui.view.layout.EmptyScreen
import com.artf.shoppinglistcompose.ui.view.menu.AppDrawer
import com.artf.shoppinglistcompose.ui.view.menu.MainMenu

@Composable
fun ShoppingListArchivedScreen(
    scaffoldState: ScaffoldState = remember {
        ScaffoldState().apply { drawerState = ArchivedShoppingListModel.drawerState }
    }
) {
    ArchivedShoppingListModel.drawerState = scaffoldState.drawerState
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreenStatus = ScreenStateAmbient.current.currentScreenStatus,
                closeDrawer = {
                    scaffoldState.drawerState = DrawerState.Closed
                    ArchivedShoppingListModel.drawerState = scaffoldState.drawerState
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
        bodyContent = { ScreenBody() }
    )
}

@Composable
private fun ScreenBody() {
    when (val result = ScreenStateAmbient.current.shoppingListsUi) {
        is ResultStatus.Success -> SuccessScreen(result.data)
        is ResultStatus.Error -> ErrorScreen()
    }
}

@Composable
private fun SuccessScreen(productList: List<ShoppingListUi>) {
    val sharedViewModelAmbient = SharedViewModelAmbient.current
    VerticalScroller {
        Column(Modifier.fillMaxWidth().padding(8.dp, 8.dp, 8.dp, 96.dp)) {
            productList.forEach { post -> ShoppingListArchivedItem(sharedViewModelAmbient, post) }
        }
    }
}

@Composable
private fun ErrorScreen() {
    EmptyScreen(
        R.string.empty_view_shopping_list_title,
        R.string.empty_view_shopping_list_subtitle
    )
}