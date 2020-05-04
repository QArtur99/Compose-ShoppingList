package com.artf.shoppinglistcompose.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.artf.shoppinglistcompose.data.database.model.Product
import com.artf.shoppinglistcompose.data.database.model.ShoppingList

@Dao
interface ShoppingListDatabaseDao {

    @Insert
    fun insertShoppingList(shoppingList: ShoppingList)

    @Update
    fun updateShoppingList(shoppingList: ShoppingList)

    @Insert
    fun insertProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)

    @Query("SELECT * FROM shopping_list WHERE isArchived IS 0 ORDER BY shoppingListTimestamp DESC")
    fun getCurrentShoppingList(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM shopping_list WHERE isArchived IS 1 ORDER BY shoppingListTimestamp DESC")
    fun getArchivedShoppingList(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM products WHERE shoppingListId = :listId ORDER BY productTimestamp DESC")
    fun getProductList(listId: Long): LiveData<List<Product>>
}
