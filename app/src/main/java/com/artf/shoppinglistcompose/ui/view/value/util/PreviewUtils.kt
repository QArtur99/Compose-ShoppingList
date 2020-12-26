package com.artf.shoppinglistcompose.ui.view.value.util

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.artf.shoppinglistcompose.ui.view.value.ShoppingListTheme

@Composable
internal fun ThemedPreview(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    ShoppingListTheme(darkTheme = darkTheme) {
        Surface {
            content()
        }
    }
}
