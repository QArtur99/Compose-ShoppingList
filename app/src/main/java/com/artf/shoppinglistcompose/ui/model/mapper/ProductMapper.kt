package com.artf.shoppinglistcompose.ui.model.mapper

import com.artf.shoppinglistcompose.data.database.model.Product
import com.artf.shoppinglistcompose.ui.model.model.ProductUi

fun List<Product>.asUiModel(): List<ProductUi> {
    return map { it.asUiModel() }
}

fun Product.asUiModel(): ProductUi {
    return ProductUi(
        id = id,
        productName = productName,
        productQuantity = productQuantity,
        productTimestamp = productTimestamp,
        shoppingListId = shoppingListId
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