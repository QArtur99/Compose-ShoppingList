package com.artf.shoppinglistcompose.data.repository

import androidx.lifecycle.LiveData
import com.artf.shoppinglistcompose.data.database.model.Product
import com.artf.shoppinglistcompose.data.database.model.ShoppingList
import com.artf.shoppinglistcompose.data.status.ResultStatus

interface ShoppingListRepository {
    suspend fun insertShoppingList(shoppingList: ShoppingList)

    suspend fun updateShoppingList(shoppingList: ShoppingList)

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    fun getCurrentShoppingList(): LiveData<ResultStatus<List<ShoppingList>>>

    fun getArchivedShoppingList(): LiveData<ResultStatus<List<ShoppingList>>>

    fun getProductList(listId: Long): LiveData<ResultStatus<List<Product>>>
}