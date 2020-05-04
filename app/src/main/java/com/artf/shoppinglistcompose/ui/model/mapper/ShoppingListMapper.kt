package com.artf.shoppinglistcompose.ui.model.mapper

import com.artf.shoppinglistcompose.data.database.model.ShoppingList
import com.artf.shoppinglistcompose.ui.model.model.ShoppingListUi

fun List<ShoppingList>.asUiModel(): List<ShoppingListUi> {
    return map { it.asUiModel() }
}

fun ShoppingList.asUiModel(): ShoppingListUi {
    return ShoppingListUi(
        id = id,
        shoppingListName = shoppingListName,
        shoppingListTimestamp = shoppingListTimestamp,
        isArchived = isArchived
    )
}

fun ShoppingListUi.asDomainModel(): ShoppingList {
    return ShoppingList(
        id = id,
        shoppingListName = shoppingListName,
        shoppingListTimestamp = shoppingListTimestamp,
        isArchived = isArchived
    )
}