package com.artf.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 1L,

    @ColumnInfo(name = "shoppingListName")
    var shoppingListName: String = "",

    @ColumnInfo(name = "shoppingListTimestamp")
    var shoppingListTimestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "isArchived")
    var isArchived: Boolean = false
)