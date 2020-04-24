package com.artf.shopinglistcompose.ui.view.layout

import androidx.compose.*
import java.lang.Exception
import java.util.ArrayDeque

/**
 * Class defining the screens we have in the app: home, article details and interests
 */
sealed class Screen {
    object ShoppingListCurrent : Screen()
    object ShoppingListArchived : Screen()
}

@Model
class ScreenBackStack() {
    var currentScreen: Screen = Screen.ShoppingListCurrent
    private val backStack = ArrayDeque<Screen>()

    init {
        push(Screen.ShoppingListCurrent)
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
        backStack.push(screen)
        currentScreen = screen
    }
}

val ScreenAmbient =
    ambientOf<ScreenBackStack> { throw IllegalStateException("backPressHandler is not initialized") }
