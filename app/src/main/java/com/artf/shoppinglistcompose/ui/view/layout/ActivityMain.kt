package com.artf.shoppinglistcompose.ui.view.layout

import android.util.Log
import androidx.compose.Composable
import androidx.compose.Providers
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.artf.shoppinglistcompose.ui.data.status.ScreenStatus
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
            Log.e("ShoppingListApp", "ShoppingListApp: ")
        }
    }
}

@Composable
private fun AppContent() {
    val sharedViewModelAmbient = SharedViewModelAmbient.current
    val screenState = observer(sharedViewModelAmbient.screenState)
    screenState ?: return

    Providers(ScreenStateAmbient provides screenState) {
        when (val screen = screenState.currentScreenStatus) {
            is ScreenStatus.CurrentShoppingList -> ShoppingListCurrentScreen()
            is ScreenStatus.ArchivedShoppingList -> ShoppingListArchivedScreen()
            is ScreenStatus.CurrentProductList -> ProductListCurrentScreen()
            is ScreenStatus.ArchivedProductList -> ArchivedProductListScreen()
        }
    }
}
