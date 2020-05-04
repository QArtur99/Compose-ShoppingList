package com.artf.shoppinglistcompose.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.artf.shoppinglistcompose.data.database.model.Product
import com.artf.shoppinglistcompose.data.database.model.ShoppingList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.artf.shoppinglistcompose.data.status.ResultStatus
import java.lang.Exception

class ShoppingListSourceImpl constructor(
    private val shoppingListDatabase: ShoppingListDatabaseDao,
    private val ioDispatcher: CoroutineDispatcher
) : ShoppingListSource {

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

    override fun getCurrentShoppingList(): LiveData<ResultStatus<List<ShoppingList>>> {
        return shoppingListDatabase.getCurrentShoppingList().map {
            if (it.isNotEmpty()) ResultStatus.Success(it) else ResultStatus.Error(Exception("ShoppingList not found!"))
        }
    }

    override fun getArchivedShoppingList(): LiveData<ResultStatus<List<ShoppingList>>> {
        return shoppingListDatabase.getArchivedShoppingList().map {
            if (it.isNotEmpty()) ResultStatus.Success(it) else ResultStatus.Error(Exception("ShoppingList not found!"))
        }
    }

    override fun getProductList(listId: Long): LiveData<ResultStatus<List<Product>>> {
        return shoppingListDatabase.getProductList(listId).map {
            if (it.isNotEmpty()) ResultStatus.Success(it) else ResultStatus.Error(Exception("Product not found!"))
        }
    }
}