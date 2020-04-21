package com.artf.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.artf.data.database.model.Product
import com.artf.data.database.model.ShoppingList

@Dao
interface ShoppingListDatabaseDao {

    @Insert
    fun insertShoppingList(shoppingList: ShoppingList)

    @Insert
    fun insertShoppingListItem(product: Product)

    @Update
    fun updateShoppingList(shoppingList: ShoppingList)

    @Update
    fun updateShoppingListItem(product: Product)

    @Delete
    fun deleteShoppingListItem(product: Product)

    @Query("SELECT * FROM shopping_list WHERE isArchived IS 0 ORDER BY shoppingListTimestamp DESC")
    fun getCurrentShoppingList(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM shopping_list WHERE isArchived IS 1 ORDER BY shoppingListTimestamp DESC")
    fun getArchivedShoppingList(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM products WHERE shoppingListId = :listId ORDER BY productTimestamp DESC")
    fun getAllShoppingListItem(listId: Long): LiveData<List<Product>>
}
