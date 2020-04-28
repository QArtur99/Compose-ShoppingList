package com.artf.shoppinglistcompose.ui.data.status

import com.artf.shoppinglistcompose.ui.data.model.ShoppingListUi

sealed class Screen {
    object CurrentShoppingList : Screen()
    object ArchivedShoppingList : Screen()
    class CurrentProductList(val shoppingList: ShoppingListUi) : Screen()
    class ArchivedProductList(val shoppingList: ShoppingListUi) : Screen()
}