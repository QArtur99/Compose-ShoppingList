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

    override suspend fun updateShoppingList(shoppingList: ShoppingList) {
        withContext(ioDispatcher) {
            shoppingListDatabase.updateShoppingList(shoppingList)
        }
    }

    override suspend fun updateShoppingListItem(product: Product) {
        withContext(ioDispatcher) {
            shoppingListDatabase.updateShoppingListItem(product)
        }
    }

    override suspend fun insertShoppingList(shoppingList: ShoppingList) {
        withContext(ioDispatcher) {
            shoppingListDatabase.insertShoppingList(shoppingList)
        }
    }

    override suspend fun insertProduct(product: Product) {
        withContext(ioDispatcher) {
            shoppingListDatabase.insertShoppingListItem(product)
        }
    }

    override suspend fun deleteProduct(product: Product) {
        withContext(ioDispatcher) {
            shoppingListDatabase.deleteShoppingListItem(product)
        }
    }

    override fun getCurrentShoppingList() = shoppingListDatabase.getCurrentShoppingList()

    override fun getArchivedShoppingList() = shoppingListDatabase.getArchivedShoppingList()

    override fun getAllShoppingListItem(listId: Long) = shoppingListDatabase.getAllShoppingListItem(listId)
}