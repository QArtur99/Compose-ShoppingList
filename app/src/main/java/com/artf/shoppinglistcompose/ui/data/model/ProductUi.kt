package com.artf.shoppinglistcompose.ui.data.model

import com.artf.shoppinglistcompose.ui.data.status.ShoppingListStatus

data class ProductUi(
    val id: Long,
    val productName: String,
    val productQuantity: Long,
    val productTimestamp: Long,
    val shoppingListId: Long,
    val shoppingListStatus: ShoppingListStatus
)