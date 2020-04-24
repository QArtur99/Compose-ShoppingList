package com.artf.shopinglistcompose.ui.view.layout

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.remember
import androidx.compose.state
import androidx.lifecycle.MutableLiveData
import androidx.ui.core.LifecycleOwnerAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.CornerSize
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.*
import androidx.ui.res.stringResource
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.artf.data.database.model.ShoppingList
import com.artf.shopinglistcompose.R
import com.artf.shopinglistcompose.ui.view.SharedViewModel
import com.artf.shopinglistcompose.ui.view.menu.MainMenu
import com.artf.shopinglistcompose.util.ShoppingListType
import org.koin.androidx.viewmodel.ext.android.viewModel

@Composable
fun ShoppingListCurrentScreen(
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    val showDialog = state { false }
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
        bodyContent = {
            ScreenBody()
            ShoppingListDialog(showDialog)
        },
        floatingActionButton = { Fab(showDialog) }
    )
}

@Composable
private fun ScreenBody() {
    val sharedViewModel = LifecycleOwnerAmbient.current.viewModel<SharedViewModel>()
    sharedViewModel.value.setShoppingListType(ShoppingListType.CURRENT)
    val posts2 = MutableLiveData<List<ShoppingList>>().apply {
        value = arrayListOf(
            ShoppingList(id = 0, shoppingListName = "A"),
            ShoppingList(id = 1, shoppingListName = "B"),
            ShoppingList(id = 2, shoppingListName = "C"),
            ShoppingList(id = 3, shoppingListName = "D"),
            ShoppingList(id = 4, shoppingListName = "E")
        )
    }

    val post3 = observer(sharedViewModel.value.shoppingLists)

    VerticalScroller {
        Column(Modifier.fillMaxWidth().padding(8.dp, 8.dp, 8.dp, 96.dp)) {
            post3?.forEach { post ->
                ShoppingListCurrentItem(sharedViewModel.value, post)
//                HomeScreenDivider()
            }
        }
    }
}

@Composable
private fun HomeScreenDivider() {
    Divider(
        modifier = Modifier.padding(start = 14.dp, end = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Composable
fun Fab(showDialog: MutableState<Boolean>) {
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