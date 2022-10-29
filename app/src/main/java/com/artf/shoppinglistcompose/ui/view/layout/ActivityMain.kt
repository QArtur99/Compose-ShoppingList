package com.artf.shoppinglistcompose.ui.view.layout

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import com.artf.shoppinglistcompose.ui.model.AmbientScreenState
import com.artf.shoppinglistcompose.ui.model.AmbientSharedViewModel
import com.artf.shoppinglistcompose.ui.model.model.ScreenUi
import com.artf.shoppinglistcompose.ui.model.state.ScreenState
import com.artf.shoppinglistcompose.ui.view.layout.archivedList.ArchivedProductListScreen
import com.artf.shoppinglistcompose.ui.view.layout.archivedList.ShoppingListArchivedScreen
import com.artf.shoppinglistcompose.ui.view.layout.currentList.ProductListCurrentScreen
import com.artf.shoppinglistcompose.ui.view.layout.currentList.ShoppingListCurrentScreen
import com.artf.shoppinglistcompose.ui.theme.ShoppingListTheme

@Composable
fun ShoppingListApp() {
    ShoppingListTheme {
        Surface(color = MaterialTheme.colors.background) {
            AppContent()
        }
    }
}

@Composable
private fun AppContent() {
    val sharedViewModelAmbient = AmbientSharedViewModel.current
    val screenState: State<ScreenUi?> = sharedViewModelAmbient.screenState.observeAsState()
    val screenStateValue = screenState.value
    screenStateValue ?: return

    CompositionLocalProvider(AmbientScreenState provides screenStateValue) {
        when (screenStateValue.currentScreenState) {
            is ScreenState.CurrentShoppingList -> ShoppingListCurrentScreen()
            is ScreenState.ArchivedShoppingList -> ShoppingListArchivedScreen()
            is ScreenState.CurrentProductList -> ProductListCurrentScreen()
            is ScreenState.ArchivedProductList -> ArchivedProductListScreen()
            else -> {}
        }
    }
}
