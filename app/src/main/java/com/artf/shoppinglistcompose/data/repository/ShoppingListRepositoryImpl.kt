package com.artf.shoppinglistcompose.data.repository

import com.artf.shoppinglistcompose.data.database.model.Product
import com.artf.shoppinglistcompose.data.database.model.ShoppingList
import com.artf.shoppinglistcompose.data.database.ShoppingListSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ShoppingListRepositoryImpl constructor(
    private val shoppingListSource: ShoppingListSource,
    private val ioDispatcher: CoroutineDispatcher
) : ShoppingListRepository {

    override suspend fun insertShoppingList(shoppingList: ShoppingList) {
        withContext(ioDispatcher) {
            shoppingListSource.insertShoppingList(shoppingList)
        }
    }

    override suspend fun updateShoppingList(shoppingList: ShoppingList) {
        withContext(ioDispatcher) {
            shoppingListSource.updateShoppingList(shoppingList)
        }
    }

    override suspend fun insertProduct(product: Product) {
        withContext(ioDispatcher) {
            shoppingListSource.insertProduct(product)
        }
    }

    override suspend fun updateProduct(product: Product) {
        withContext(ioDispatcher) {
            shoppingListSource.updateProduct(product)
        }
    }

    override suspend fun deleteProduct(product: Product) {
        withContext(ioDispatcher) {
            shoppingListSource.deleteProduct(product)
        }
    }

    override fun getCurrentShoppingList() = shoppingListSource.getCurrentShoppingList()

    override fun getArchivedShoppingList() = shoppingListSource.getArchivedShoppingList()

    override fun getProductList(listId: Long) = shoppingListSource.getProductList(listId)
}