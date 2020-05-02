package com.artf.shoppinglistcompose.ui.view.layout

import androidx.compose.Composable
import androidx.compose.Providers
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.artf.shoppinglistcompose.ui.data.state.ScreenState
import com.artf.shoppinglistcompose.ui.data.ScreenStateAmbient
import com.artf.shoppinglistcompose.ui.data.SharedViewModelAmbient
import com.artf.shoppinglistcompose.ui.view.layout.archivedList.ArchivedProductListScreen
import com.artf.shoppinglistcompose.ui.view.layout.archivedList.ShoppingListArchivedScreen
import com.artf.shoppinglistcompose.ui.view.layout.currentList.ProductListCurrentScreen
import com.artf.shoppinglistcompose.ui.view.layout.currentList.ShoppingListCurrentScreen
import com.artf.shoppinglistcompose.ui.view.value.lightThemeColors
import com.artf.shoppinglistcompose.ui.view.value.themeTypography
import com.artf.shoppinglistcompose.util.observer

@Composable
fun ShoppingListApp() {
    MaterialTheme(
        colors = lightThemeColors,
        typography = themeTypography
    ) {
        Surface(color = MaterialTheme.colors.background) {
            AppContent()
        }
    }
}

@Composable
private fun AppContent() {
    val sharedViewModelAmbient = SharedViewModelAmbient.current
    val screenState = observer(sharedViewModelAmbient.screenState)
    screenState ?: return

    Providers(ScreenStateAmbient provides screenState) {
        when (screenState.currentScreenState) {
            is ScreenState.CurrentShoppingList -> ShoppingListCurrentScreen()
            is ScreenState.ArchivedShoppingList -> ShoppingListArchivedScreen()
            is ScreenState.CurrentProductList -> ProductListCurrentScreen()
            is ScreenState.ArchivedProductList -> ArchivedProductListScreen()
        }
    }
}
