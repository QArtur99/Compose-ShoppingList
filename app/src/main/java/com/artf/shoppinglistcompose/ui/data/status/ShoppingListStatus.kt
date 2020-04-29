package com.artf.shoppinglistcompose.ui.data.status

sealed class ShoppingListStatus {
    object CURRENT : ShoppingListStatus()
    object ARCHIVED : ShoppingListStatus()
}