package com.artf.shoppinglistcompose.ui.data

import androidx.lifecycle.MutableLiveData
import com.artf.shoppinglistcompose.ui.data.status.ScreenStatus
import java.util.ArrayDeque

class ScreenBackStackImpl : ScreenBackStack {

    private var currentScreenStatus: MutableLiveData<ScreenStatus> = MutableLiveData<ScreenStatus>()
    private val backStack = ArrayDeque<ScreenStatus>()

    init {
        pushBackStack(ScreenStatus.CurrentShoppingList)
    }

    fun getCurrentScreen(): MutableLiveData<ScreenStatus> {
        return currentScreenStatus
    }

    override fun popBackStack(): ScreenStatus? {
        return try {
            if (backStack.size == 1) return null
            backStack.pop()
            backStack.peekFirst()?.also { currentScreenStatus.value = it }
        } catch (e: Exception) {
            null
        }
    }

    override fun pushBackStack(screenStatus: ScreenStatus) {
        if (screenStatus != currentScreenStatus.value) {
            backStack.push(screenStatus)
            currentScreenStatus.value = screenStatus
        }
    }
}

interface ScreenBackStack {
    fun popBackStack(): ScreenStatus?
    fun pushBackStack(screenStatus: ScreenStatus)
}