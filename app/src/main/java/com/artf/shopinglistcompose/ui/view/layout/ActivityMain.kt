package com.artf.shopinglistcompose.ui.view.layout

import androidx.compose.Composable
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.artf.shopinglistcompose.ui.data.Screen
import com.artf.shopinglistcompose.ui.data.ScreenBackStackAmbient
import com.artf.shopinglistcompose.ui.view.layout.archivedList.ArchivedProductListScreen
import com.artf.shopinglistcompose.ui.view.layout.archivedList.ShoppingListArchivedScreen
import com.artf.shopinglistcompose.ui.view.layout.currentList.ProductListCurrentScreen
import com.artf.shopinglistcompose.ui.view.layout.currentList.ShoppingListCurrentScreen
import com.artf.shopinglistcompose.ui.view.value.lightThemeColors
import com.artf.shopinglistcompose.ui.view.value.themeTypography

@Composable
fun ShoppingListApp() {
    MaterialTheme(
        colors = lightThemeColors,
        typography = themeTypography
    ) { AppContent() }
}

@Composable
private fun AppContent() {
    Surface(color = MaterialTheme.colors.background) {
        when (val screen = ScreenBackStackAmbient.current.currentScreen) {
            is Screen.ShoppingListCurrent -> ShoppingListCurrentScreen()
            is Screen.ShoppingListArchived -> ShoppingListArchivedScreen()
            is Screen.ProductListCurrent -> ProductListCurrentScreen(screen.shoppingList)
            is Screen.ProductListArchived -> ArchivedProductListScreen(screen.shoppingList)
        }
    }
}
