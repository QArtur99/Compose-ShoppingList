package com.artf.shoppinglistcompose.ui.data.status

import com.artf.shoppinglistcompose.ui.data.model.ShoppingListUi

sealed class ScreenStatus {
    object CurrentShoppingList : ScreenStatus()
    object ArchivedShoppingList : ScreenStatus()
    class CurrentProductList(val shoppingList: ShoppingListUi) : ScreenStatus()
    class ArchivedProductList(val shoppingList: ShoppingListUi) : ScreenStatus()
}