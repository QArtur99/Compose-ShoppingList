package com.artf.shopinglistcompose.ui.view.layout.archivedList

import androidx.compose.Composable
import androidx.compose.remember
import androidx.compose.state
import androidx.ui.core.LifecycleOwnerAmbient
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
import com.artf.data.database.model.ShoppingList
import com.artf.shopinglistcompose.R
import com.artf.shopinglistcompose.ui.view.SharedViewModel
import com.artf.shopinglistcompose.ui.view.layout.currentList.ProductCurrentItem
import com.artf.shopinglistcompose.ui.view.layout.currentList.ShoppingListDialog
import com.artf.shopinglistcompose.ui.view.layout.observer
import com.artf.shopinglistcompose.ui.view.menu.MainMenu
import org.koin.androidx.viewmodel.ext.android.viewModel

@Composable
fun ArchivedProductListScreen(
    shoppingList: ShoppingList,
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
        bodyContent = { ScreenBody(shoppingList) }
    )
}

@Composable
private fun ScreenBody(shoppingList: ShoppingList) {
    val sharedViewModel = LifecycleOwnerAmbient.current.viewModel<SharedViewModel>()
    sharedViewModel.value.onShoppingListClick(shoppingList)
    val productList = observer(sharedViewModel.value.productListUi)

    VerticalScroller {
        Column(Modifier.fillMaxWidth().padding(8.dp, 8.dp, 8.dp, 96.dp)) {
            productList?.forEach { post -> ArchivedProductItem(sharedViewModel.value, post) }
        }
    }
}