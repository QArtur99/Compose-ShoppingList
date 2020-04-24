package com.artf.shopinglistcompose.ui.view.layout

import androidx.compose.Composable
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface

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
        when (ScreenAmbient.current.currentScreen) {
            is Screen.ShoppingListCurrent -> ShoppingListCurrentScreen()
            is Screen.ShoppingListArchived -> ShoppingListArchivedScreen()
        }
    }
}

