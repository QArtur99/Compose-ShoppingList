package com.artf.shoppinglistcompose

import androidx.compose.Composable
import androidx.compose.Providers
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.test.ComposeTestRule
import androidx.ui.test.SemanticsNodeInteraction
import androidx.ui.test.findAll
import androidx.ui.test.hasSubstring
import com.artf.shoppinglistcompose.ui.data.SharedViewModel
import com.artf.shoppinglistcompose.ui.data.SharedViewModelAmbient
import com.artf.shoppinglistcompose.ui.view.layout.ShoppingListApp

fun ComposeTestRule.launchApp(mainThreadCallBack: () -> SharedViewModel) {
    setContent {
        val sharedViewModel = mainThreadCallBack()
        Providers(
            SharedViewModelAmbient provides sharedViewModel
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
    return findAll(
        hasSubstring(text, ignoreCase)
    )
}