package com.artf.shoppinglistcompose.ui.data

import androidx.lifecycle.MutableLiveData
import com.artf.shoppinglistcompose.ui.data.state.ScreenState
import java.util.ArrayDeque

class ScreenBackStackImpl : ScreenBackStack {

    private val currentScreenState = MutableLiveData<ScreenState>()
    private val backStack = ArrayDeque<ScreenState>()

    init {
        pushBackStack(ScreenState.CurrentShoppingList)
    }

    fun getCurrentScreen(): MutableLiveData<ScreenState> {
        return currentScreenState
    }

    override fun popBackStack(): ScreenState? {
        if (backStack.size == 1) return null
        backStack.pop()
        return backStack.peekFirst()?.also { currentScreenState.value = it }
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