package com.artf.shoppinglistcompose.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.artf.shoppinglistcompose.MainCoroutineRule
import com.artf.shoppinglistcompose.data.database.ShoppingListDatabase
import com.artf.shoppinglistcompose.data.database.ShoppingListDatabaseDao
import com.artf.shoppinglistcompose.data.database.model.Product
import com.artf.shoppinglistcompose.data.database.model.ShoppingList
import com.artf.shoppinglistcompose.util.LiveDataTestUtil.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = "AndroidManifest.xml")
class ShoppingListDatabaseDaoRoboTest {

    private lateinit var database: ShoppingListDatabase
    private lateinit var dao: ShoppingListDatabaseDao

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.systemContext,
            ShoppingListDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.shoppingListDatabaseDao
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertShoppingList() = runBlockingTest {
        val testName = "TestName"
        val shoppingList = ShoppingList(
            shoppingListName = testName,
            isArchived = false
        )
        dao.insertShoppingList(shoppingList)
        val loaded = getValue(dao.getCurrentShoppingList())[0]
        assertThat(loaded.shoppingListName, `is`(shoppingList.shoppingListName))
    }

    @Test
    fun updateShoppingList() = runBlockingTest {
        val testName = "TestName"
        var shoppingList = ShoppingList().apply { isArchived = false }
        dao.insertShoppingList(shoppingList)
        shoppingList = getValue(dao.getCurrentShoppingList())[0]
        dao.updateShoppingList(shoppingList.apply { shoppingListName = testName })
        val loaded = getValue(dao.getCurrentShoppingList())[0]
        assertThat(loaded.shoppingListName, `is`(shoppingList.shoppingListName))
    }

    @Test
    fun insertProduct() = runBlockingTest {
        val testName = "TestName"
        val product = Product(
            productName = testName,
            shoppingListId = 1L
        )
        dao.insertProduct(product)
        val loaded = getValue(dao.getProductList(product.shoppingListId))[0]
        assertThat(loaded.productName, `is`(product.productName))
    }

    @Test
    fun updateProduct() = runBlockingTest {
        val testProductName = "TestName"
        var product = Product().apply { shoppingListId = 1L }
        dao.insertProduct(product)
        product = getValue(dao.getProductList(product.shoppingListId))[0]
        dao.updateProduct(product.apply { productName = testProductName })
        val loaded = getValue(dao.getProductList(product.shoppingListId))[0]
        assertThat(loaded.productName, `is`(product.productName))
    }

    @Test
    fun deleteProduct() = runBlockingTest {
        var product = Product().apply { shoppingListId = 1L }
        dao.insertProduct(product)
        product = getValue(dao.getProductList(product.shoppingListId))[0]
        dao.deleteProduct(product)
        val loaded = getValue(dao.getProductList(product.shoppingListId))
        assertThat(loaded.isEmpty(), `is`(true))
    }

    @Test
    fun getCurrentShoppingList() {
        val size = 10
        (1..size).forEach {
            val shoppingList = ShoppingList(id = it.toLong()).apply { isArchived = false }
            dao.insertShoppingList(shoppingList)
        }
        val loaded = getValue(dao.getCurrentShoppingList())
        assertThat(loaded.size, `is`(size))
    }

    @Test
    fun getArchivedShoppingList() {
        val size = 10
        (1..size).forEach {
            val shoppingList = ShoppingList(id = it.toLong()).apply { isArchived = true }
            dao.insertShoppingList(shoppingList)
        }
        val loaded = getValue(dao.getArchivedShoppingList())
        assertThat(loaded.size, `is`(size))
    }

    @Test
    fun getProductList() {
        val listId = 1L
        val size = 10
        (1..size).forEach {
            val product = Product(id = it.toLong()).apply { shoppingListId = listId }
            dao.insertProduct(product)
        }
        val loaded = getValue(dao.getProductList(listId))
        assertThat(loaded.size, `is`(size))
    }
}