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
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.data.SharedViewModelAmbient
import com.artf.shoppinglistcompose.ui.view.layout.EmptyScreen
import com.artf.shoppinglistcompose.ui.view.menu.MainMenu
import com.artf.shoppinglistcompose.util.ShoppingListType
import com.artf.shoppinglistcompose.util.observer

@Composable
fun ShoppingListArchivedScreen(
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    Scaffold(
        scaffoldState = scaffoldState,
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
    val sharedViewModelAmbient = SharedViewModelAmbient.current
    sharedViewModelAmbient.setShoppingListType(ShoppingListType.ARCHIVED)
    val postList = observer(sharedViewModelAmbient.shoppingListsUi)
    if (postList == null || postList.isEmpty()) {
        EmptyScreen(
            R.string.empty_view_shopping_list_title,
            R.string.empty_view_shopping_list_subtitle
        )
        return
    }

    VerticalScroller {
        Column(Modifier.fillMaxWidth().padding(8.dp, 8.dp, 8.dp, 96.dp)) {
            postList.forEach { post -> ShoppingListArchivedItem(sharedViewModelAmbient, post) }
        }
    }
}