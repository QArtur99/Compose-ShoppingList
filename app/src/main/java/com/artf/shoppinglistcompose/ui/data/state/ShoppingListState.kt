package com.artf.shoppinglistcompose.ui.data.state

sealed class ShoppingListState {
    object CURRENT : ShoppingListState()
    object ARCHIVED : ShoppingListState()
}