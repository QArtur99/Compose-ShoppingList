package com.artf.shoppinglistcompose.ui.data.model

import com.artf.shoppinglistcompose.util.ShoppingListType

data class ProductUi(
    val id: Long,
    val productName: String,
    val productQuantity: Long,
    val productTimestamp: Long,
    val shoppingListId: Long,
    val shoppingListType: ShoppingListType
)