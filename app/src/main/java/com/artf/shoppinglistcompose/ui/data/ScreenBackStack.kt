package com.artf.shoppinglistcompose.ui.data

import androidx.lifecycle.MutableLiveData
import com.artf.shoppinglistcompose.ui.data.status.Screen
import java.util.ArrayDeque

class ScreenBackStackImpl : ScreenBackStack {

    private var currentScreen: MutableLiveData<Screen> = MutableLiveData<Screen>()
    private val backStack = ArrayDeque<Screen>()

    init {
        pushBackStack(Screen.CurrentShoppingList)
    }

    fun getCurrentScreen(): MutableLiveData<Screen> {
        return currentScreen
    }

    override fun popBackStack(): Screen? {
        return try {
            if (backStack.size == 1) return null
            backStack.pop()
            backStack.peekFirst()?.also { currentScreen.value = it }
        } catch (e: Exception) {
            null
        }
    }

    override fun pushBackStack(screen: Screen) {
        if (screen != currentScreen.value) {
            backStack.push(screen)
            currentScreen.value = screen
        }
    }
}

interface ScreenBackStack {
    fun popBackStack(): Screen?
    fun pushBackStack(screen: Screen)
}