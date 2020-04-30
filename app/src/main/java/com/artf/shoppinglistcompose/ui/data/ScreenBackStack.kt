package com.artf.shoppinglistcompose.ui.data

import androidx.lifecycle.MutableLiveData
import com.artf.shoppinglistcompose.ui.data.state.ScreenState
import java.util.ArrayDeque

class ScreenBackStackImpl : ScreenBackStack {

    private var currentScreenState: MutableLiveData<ScreenState> = MutableLiveData<ScreenState>()
    private val backStack = ArrayDeque<ScreenState>()

    init {
        pushBackStack(ScreenState.CurrentShoppingList)
    }

    fun getCurrentScreen(): MutableLiveData<ScreenState> {
        return currentScreenState
    }

    override fun popBackStack(): ScreenState? {
        return try {
            if (backStack.size == 1) return null
            backStack.pop()
            backStack.peekFirst()?.also { currentScreenState.value = it }
        } catch (e: Exception) {
            null
        }
    }

    override fun pushBackStack(screenState: ScreenState) {
        if (screenState != currentScreenState.value) {
            backStack.push(screenState)
            currentScreenState.value = screenState
        }
    }
}

interface ScreenBackStack {
    fun popBackStack(): ScreenState?
    fun pushBackStack(screenState: ScreenState)
}