package com.artf.shoppinglistcompose.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "productName")
    var productName: String = "",

    @ColumnInfo(name = "currentQuantity")
    var productQuantity: Long = -1L,

    @ColumnInfo(name = "productTimestamp")
    var productTimestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "shoppingListId")
    var shoppingListId: Long = 0L
)