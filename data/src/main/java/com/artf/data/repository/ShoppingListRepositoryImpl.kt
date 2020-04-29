package com.artf.data.repository

import com.artf.data.database.model.Product
import com.artf.data.database.model.ShoppingList
import com.artf.data.database.ShoppingListDatabaseDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ShoppingListRepositoryImpl constructor(
    private val shoppingListDatabase: ShoppingListDatabaseDao,
    private val ioDispatcher: CoroutineDispatcher
) : ShoppingListRepository {

    override suspend fun insertShoppingList(shoppingList: ShoppingList) {
        withContext(ioDispatcher) {
            shoppingListDatabase.insertShoppingList(shoppingList)
        }
    }

    override suspend fun updateShoppingList(shoppingList: ShoppingList) {
        withContext(ioDispatcher) {
            shoppingListDatabase.updateShoppingList(shoppingList)
        }
    }

    override suspend fun insertProduct(product: Product) {
        withContext(ioDispatcher) {
            shoppingListDatabase.insertProduct(product)
        }
    }

    override suspend fun updateProduct(product: Product) {
        withContext(ioDispatcher) {
            shoppingListDatabase.updateProduct(product)
        }
    }

    override suspend fun deleteProduct(product: Product) {
        withContext(ioDispatcher) {
            shoppingListDatabase.deleteProduct(product)
        }
    }

    override fun getCurrentShoppingList() = shoppingListDatabase.getCurrentShoppingList()

    override fun getArchivedShoppingList() = shoppingListDatabase.getArchivedShoppingList()

    override fun getProductList(listId: Long) = shoppingListDatabase.getProductList(listId)
}