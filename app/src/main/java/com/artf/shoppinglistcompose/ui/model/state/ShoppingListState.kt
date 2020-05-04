package com.artf.shoppinglistcompose.ui.model.state

sealed class ShoppingListState {
    object CURRENT : ShoppingListState()
    object ARCHIVED : ShoppingListState()
}