package com.artf.shoppinglistcompose.ui.data.mapper

import com.artf.data.database.model.Product
import com.artf.shoppinglistcompose.ui.data.model.ProductUi
import com.artf.shoppinglistcompose.ui.data.status.ShoppingListStatus

fun List<Product>.asUiModel(shoppingListStatus: ShoppingListStatus): List<ProductUi> {
    return map { it.asUiModel(shoppingListStatus) }
}

fun Product.asUiModel(shoppingListStatus: ShoppingListStatus): ProductUi {
    return ProductUi(
        id = id,
        productName = productName,
        productQuantity = productQuantity,
        productTimestamp = productTimestamp,
        shoppingListId = shoppingListId,
        shoppingListStatus = shoppingListStatus
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