package com.artf.shoppinglistcompose.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.artf.shoppinglistcompose.data.database.model.Product
import com.artf.shoppinglistcompose.data.database.model.ShoppingList

@Database(entities = [ShoppingList::class, Product::class], version = 2, exportSchema = false)
abstract class ShoppingListDatabase : RoomDatabase() {

    abstract val shoppingListDatabaseDao: ShoppingListDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ShoppingListDatabase? = null

        fun getInstance(context: Context): ShoppingListDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShoppingListDatabase::class.java,
                        "shopping_list_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}