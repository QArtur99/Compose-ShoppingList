package com.artf.shoppinglistcompose.ui.data

import androidx.compose.Model
import com.artf.shoppinglistcompose.ui.data.model.ShoppingListUi
import java.util.ArrayDeque

sealed class Screen {
    object ShoppingListCurrent : Screen()
    object ShoppingListArchived : Screen()
    class ProductListCurrent(val shoppingList: ShoppingListUi) : Screen()
    class ProductListArchived(val shoppingList: ShoppingListUi) : Screen()
}

@Model
class ScreenBackStack {
    var currentScreen: Screen = Screen.ShoppingListCurrent
    private val backStack = ArrayDeque<Screen>()

    init {
        backStack.push(currentScreen)
    }

    fun pop(): Screen? {
        return try {
            backStack.pop()
            backStack.peekFirst()?.also { currentScreen = it }
        } catch (e: Exception) {
            null
        }
    }

    fun push(screen: Screen) {
        if (screen != currentScreen) {
            backStack.push(screen)
            currentScreen = screen
        }
    }
}