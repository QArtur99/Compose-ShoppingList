package com.artf.shopinglistcompose.ui.data.model

import com.artf.shopinglistcompose.util.ShoppingListType

data class ProductUi(
    val id: Long,
    val productName: String,
    val productQuantity: Long,
    val productTimestamp: Long,
    val shoppingListId: Long,
    val shoppingListType: ShoppingListType
)