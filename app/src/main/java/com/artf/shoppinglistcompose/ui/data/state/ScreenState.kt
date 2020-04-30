package com.artf.shoppinglistcompose.ui.data.state

import com.artf.shoppinglistcompose.ui.data.model.ShoppingListUi

sealed class ScreenState {
    object CurrentShoppingList : ScreenState()
    object ArchivedShoppingList : ScreenState()
    class CurrentProductList(val shoppingList: ShoppingListUi) : ScreenState()
    class ArchivedProductList(val shoppingList: ShoppingListUi) : ScreenState()
}