package com.artf.shopinglistcompose.ui.data

import com.artf.data.database.model.Product
import com.artf.shopinglistcompose.ui.data.model.ProductUi
import com.artf.shopinglistcompose.util.ShoppingListType

fun List<Product>.asUiModel(shoppingListType: ShoppingListType): List<ProductUi> {
    return map { it.asUiModel(shoppingListType) }
}

fun Product.asUiModel(shoppingListType: ShoppingListType): ProductUi {
    return ProductUi(
        id = id,
        productName = productName,
        productQuantity = productQuantity,
        productTimestamp = productTimestamp,
        shoppingListId = shoppingListId,
        shoppingListType = shoppingListType
    )
}

fun ProductUi.asDbModel(): Product {
    return Product(
        id = id,
        productName = productName,
        productQuantity = productQuantity,
        productTimestamp = productTimestamp,
        shoppingListId = shoppingListId
    )
}