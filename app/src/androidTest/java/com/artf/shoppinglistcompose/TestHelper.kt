package com.artf.shoppinglistcompose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.artf.shoppinglistcompose.ui.model.AmbientSharedViewModel
import com.artf.shoppinglistcompose.ui.model.SharedViewModel
import com.artf.shoppinglistcompose.ui.view.layout.ShoppingListApp

fun ComposeTestRule.launchApp(mainThreadCallBack: @Composable() () -> SharedViewModel) {
    setContent {
        val sharedViewModel = mainThreadCallBack()
        Providers(
            AmbientSharedViewModel provides sharedViewModel
        ) { ShoppingListApp() }
    }
}

fun ComposeTestRule.setMaterialContent(children: @Composable() () -> Unit) {
    setContent {
        MaterialTheme {
            Surface {
                children()
            }
        }
    }
}

fun findAllBySubstring(text: String, ignoreCase: Boolean = false): List<SemanticsNodeInteraction> {
    return findAllBySubstring(text, ignoreCase)
}
