package com.artf.data.repository

import androidx.lifecycle.LiveData
import com.artf.data.database.model.Product
import com.artf.data.database.model.ShoppingList

interface ShoppingListRepository {
    suspend fun updateShoppingList(shoppingList: ShoppingList)

    suspend fun updateShoppingListItem(product: Product)

    suspend fun insertShoppingList(shoppingList: ShoppingList)

    suspend fun insertProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    fun getCurrentShoppingList(): LiveData<List<ShoppingList>>

    fun getArchivedShoppingList(): LiveData<List<ShoppingList>>

    fun getAllShoppingListItem(listId: Long): LiveData<List<Product>>
}