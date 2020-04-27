package com.artf.shoppinglistcompose.ui.data.mapper

import com.artf.data.database.model.Product
import com.artf.shoppinglistcompose.ui.data.model.ProductUi
import com.artf.shoppinglistcompose.util.ShoppingListType

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

fun ProductUi.asDomainModel(): Product {
    return Product(
        id = id,
        productName = productName,
        productQuantity = productQuantity,
        productTimestamp = productTimestamp,
        shoppingListId = shoppingListId
    )
}