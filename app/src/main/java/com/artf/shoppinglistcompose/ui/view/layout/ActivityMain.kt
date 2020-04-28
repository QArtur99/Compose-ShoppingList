package com.artf.shoppinglistcompose.ui.view.layout

import android.util.Log
import androidx.compose.Composable
import androidx.compose.Providers
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.artf.shoppinglistcompose.ui.data.status.Screen
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
    ) { AppContent() }
}

@Composable
private fun AppContent() {
    val sharedViewModelAmbient = SharedViewModelAmbient.current
    val screenState = observer(sharedViewModelAmbient.screenState)
    screenState ?: return

    Surface(color = MaterialTheme.colors.background) {
        Providers(ScreenStateAmbient provides screenState) {
            when (val screen = screenState.currentScreen) {
                is Screen.CurrentShoppingList -> {
                    Log.e("TAG", "AppContent")
                    ShoppingListCurrentScreen()
                }
                is Screen.ArchivedShoppingList -> ShoppingListArchivedScreen()
                is Screen.CurrentProductList -> ProductListCurrentScreen()
                is Screen.ArchivedProductList -> ArchivedProductListScreen()
            }
        }
    }
}
